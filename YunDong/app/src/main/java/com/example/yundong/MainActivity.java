package com.example.yundong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothSocket;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.UriMatcher;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.location.Criteria;
import android.location.GnssStatus;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity implements Fragment_Device.OnFragmentInteractionListener,
        Fragment_info.OnFragmentInteractionListener,Fragment_Sport.OnFragmentInteractionListener,
        Fragment_My.OnFragmentInteractionListener,Fragment_info_s1.OnFragmentInteractionListener,
        Fragment_info_s2.OnFragmentInteractionListener {
    private BottomNavigationView mNav;
    private Fragment_Sport mFrgmtSport;
    private Fragment mFrgmtInfo, mFrgmtDevice, mFrgmtMy;
    private Fragment[] mFrgmtArray;
    private int mCurFrag = 0;
    private MyReceiver myReceiver;

    private ContentResolver mContentResolver;
    private MyContentObserver mInternalObserver;
    private MyContentObserver mExternalObserver;


    private MyService mMyService;
    private MyService.MyBinder mMyBinder;
    private ServiceConnection mSerconn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mMyBinder = (MyService.MyBinder) iBinder;//获得接口实例引用
            mMyService = mMyBinder.getService();//获得服务上下文
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mMyBinder = null;//服务接口置null，表示服务已经被动解绑
            mMyService = null;//服务上下文置null，表示服务已经被动解绑
        }
    };
    private Handler mHandler;
    private HandlerThread mWorkThread;
    private WorkThreadHandler mWorkHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //隐藏ActionBar
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        //步骤0：动态申请权限
        getPermissonDoNotDisturb();//获得免打扰权限，才能控制音量
        getPermissionReadStorage();//获得读存储权限

        //步骤1：Handler定义
        mHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case AppConfig.MAIN_SCREEN_SHOT://处理截图
                        Toast.makeText(MainActivity.this, "监听到截屏，图片保存在\n：" + msg.obj, Toast.LENGTH_SHORT).show();
                        break;
                    case AppConfig.MAIN_UPDATE_TIANQI://更新UI天气
                        mFrgmtSport.updateTianqi((List<Map<String, String>>)msg.obj);
                        Toast.makeText(MainActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                        break;
                }
                super.handleMessage(msg);
            }
        };

        //步骤：注册内容观察者
        mContentResolver = this.getContentResolver();
        mInternalObserver = new MyContentObserver(mHandler, mContentResolver);
        mExternalObserver = new MyContentObserver(mHandler, mContentResolver);
        mContentResolver.registerContentObserver(MediaStore.Images.Media.INTERNAL_CONTENT_URI, false, mInternalObserver);
        mContentResolver.registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, false, mExternalObserver);

        //步骤：创建后台线程
        mWorkThread = new HandlerThread("MyHandlerThread");
        mWorkThread.start();
        mWorkHandler = new WorkThreadHandler(mHandler, mWorkThread.getLooper());

        //注册广播
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        this.registerReceiver(myReceiver, intentFilter);

        //注意下面代码顺序是根据对象创建时间规划好的，如果更改顺序，可能会逻辑错误，导致运行时出错
        //创建Fragment，并放入数组，然后加入到Fragment管理器中，4个全加入，加入后先隐藏
        mFrgmtDevice = new Fragment_Device();
        mFrgmtInfo = new Fragment_info();
        mFrgmtMy = new Fragment_My();
        mFrgmtSport = new Fragment_Sport();
        mFrgmtArray = new Fragment[]{mFrgmtSport, mFrgmtInfo, mFrgmtDevice, mFrgmtMy};
        getSupportFragmentManager().beginTransaction().add(R.id.fmlayout_main, mFrgmtSport).hide(mFrgmtSport)
                .add(R.id.fmlayout_main, mFrgmtInfo).hide(mFrgmtInfo)
                .add(R.id.fmlayout_main, mFrgmtDevice).hide(mFrgmtDevice)
                .add(R.id.fmlayout_main, mFrgmtMy).hide(mFrgmtMy).commit();
        //底部导航栏初始化，设置监听事件代码，在监听事件代码中切换显示fragment，监听代码完成后，设置选中运动menuitem项
        mNav = findViewById(R.id.botmview_main);
        mNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int last = mCurFrag;
                switch (menuItem.getItemId()) {
                    case R.id.navbomitem_sport:
                        mCurFrag = 0;
                        getSupportFragmentManager().beginTransaction().hide(mFrgmtArray[last])
                                .show(mFrgmtArray[mCurFrag]).commit();
                        return true;
                    case R.id.navbomitem_info:
                        mCurFrag = 1;
                        getSupportFragmentManager().beginTransaction().hide(mFrgmtArray[last])
                                .show(mFrgmtArray[mCurFrag]).commit();
                        return true;
                    case R.id.navbomitem_device:
                        mCurFrag = 2;
                        getSupportFragmentManager().beginTransaction().hide(mFrgmtArray[last])
                                .show(mFrgmtArray[mCurFrag]).commit();
                        return true;
                    case R.id.navbomitem_my:
                        mCurFrag = 3;
                        getSupportFragmentManager().beginTransaction().hide(mFrgmtArray[last])
                                .show(mFrgmtArray[mCurFrag]).commit();
                        return true;
                }

                return false;
            }
        });
        mNav.setSelectedItemId(R.id.navbomitem_sport);

        //常量UriMatcher.NO_MATCH表示不匹配任何路径的返回码
        UriMatcher sMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //如果match()方法匹配content://com.example.yundong.MyProvider/person路径，返回匹配码为1
        sMatcher.addURI("com.example.yundong.MyProvider", "person", 1);//添加需要匹配uri，如果匹配就会返回匹配码
        //如果match()方法匹配content://com.example.yundong.MyProvider/person/230路径，返回匹配码为2
        sMatcher.addURI("com.example.yundong.MyProvider", "person/#", 2);//#号为通配符
        switch (sMatcher.match(Uri.parse("content://com.example.yundong.MyProvider/person/10"))) {
            case 1:
                break;
            case 2:
                Toast.makeText(this, "uri match 2", Toast.LENGTH_SHORT).show();
                break;
            default://不匹配
                break;
        }


        Uri uri = Uri.parse("content://com.example.yundong.MyProvider/person/20");
        Uri resultUri = ContentUris.withAppendedId(uri, 10);//生成后的Uri为：content://com.example.yundong.MyProvider/person/20/10
        long personid = ContentUris.parseId(resultUri);//获取的结果为:10

        Intent intent = new Intent(this, MyService.class);
        if (mMyService == null)//本应用中逻辑判断，防止重复绑定
            bindService(intent, mSerconn, Service.BIND_AUTO_CREATE);


    }

    @Override
    protected void onDestroy() {
        if (null != myReceiver)
            unregisterReceiver(myReceiver);//注销广播接受者
        myReceiver = null;
        if (mMyService != null) {//本应用中逻辑判断，防止空解绑
            mMyService = null;
            unbindService(mSerconn);
        }
        if (mContentResolver != null) {
            mContentResolver.unregisterContentObserver(mInternalObserver);
            mContentResolver.unregisterContentObserver(mExternalObserver);
            mInternalObserver.myRelease();
            mExternalObserver.myRelease();
        }
        if (null != mWorkThread) {
            mWorkThread.quit();
            mWorkThread = null;
        }
        mWorkHandler = null;

        super.onDestroy();
    }

    //选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //选项菜单监听事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {//主页面不出栈，所以不调用finish()方法
            case R.id.menu_main_register:
                Intent intent = new Intent("com.example.yundong.SHOW_Register_Activity");
                startActivity(intent);
                break;
            case R.id.menu_main_login:
                Intent intent2 = new Intent("com.example.yundong.SHOW_Login_Activity");
                startActivity(intent2);
                break;
            case R.id.menu_main_exit:
                finish();
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {
        //Log.d("YDHL",uri.toString());
//        new Thread(){
//            @Override
//            public void run() {
//                AppConfig.testhttpPOST();
//
//            }
//        }.start();
        //高德地图测试
        //Intent intent = new Intent(this,MapActivity.class);
        // startActivity(intent);
        mWorkHandler.sendEmptyMessage(AppConfig.WORKTHREAD_UPDATE_TIANQI);
        Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public Bundle onFragCmdCalled(int cmd, Bundle bundle) {
        switch (cmd) {
            case AppConfig.MUSIC_PLAY:
                mMyBinder.music_play();//调用服务播放
                break;
            case AppConfig.MUSIC_PAUSE:
                mMyBinder.music_pause();//调用服务暂停
                break;
            case AppConfig.MUSIC_STOP:
                mMyBinder.music_stop();//调用服务停止
                break;
        }
        return null;
    }

    //获得动态申请权限结果
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 110: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 权限通过了
                } else {
                    // 权限拒绝了
                }
                return;
            }
        }
    }


    //请求免打扰权限,才可进行音量操作
    private void getPermissonDoNotDisturb() {
        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N //N=24，对应安卓7.0
                && !notificationManager.isNotificationPolicyAccessGranted()) {
            Intent intent = new Intent(
                    android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            startActivity(intent);
        }
    }

    //请求读外存权限
    private void getPermissionReadStorage() {
        if (Build.VERSION.SDK_INT > 22) {
            List<String> permissionList = new ArrayList<>();
            // 检查是否有内存权限
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                ActivityCompat.requestPermissions(this,
                        permissionList.toArray(new String[permissionList.size()]), 110);
            } else {
                // 已经获得了权限
            }
        }
    }
}
    ///////////////////////////////////
    /*          sensor example------------1
    private SensorManager sensorManager;
    //获得传感器管理器
    sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    //获得设备上所有的传感器，返回传感器对象列表
    List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
    //获得设备上所有的重力传感器，返回重力传感器对象列表
    List<Sensor> gravitySensors = sensorManager.getSensorList(Sensor.TYPE_GRAVITY);
    //获得设备上默认磁场传感器
    List<Sensor> magneticSensors = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    if(magneticSensors !=null){
        // 设备支持磁场传感器
    } else{
        // 设备不支持磁场传感器
    }

    //使用传感器的Activity窗口类，实现了传感器事件监听接口，监听光照传感器
    public class SensorActivity extends Activity implements SensorEventListener {
        private SensorManager sensorManager;
        private Sensor mLight;

        @Override
        public final void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);

            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }
        //接口方法，传感器精度变化后回调
        @Override
        public final void onAccuracyChanged(Sensor sensor, int accuracy) {
            // 精度变化后，在此编写处理代码
        }
        //接口方法，传感器报告数据时回调
        @Override
        public final void onSensorChanged(SensorEvent event) {
            // 获得光照数据，只有一个数据，放在数组第一个元素中
            float lux = event.values[0];
        }
        //窗口生命期方法：恢复运行时，注册传感器监听器
        @Override
        protected void onResume() {
            super.onResume();
            sensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        }
        //窗口生命期方法：暂停运行时，注销传感器监听器
        @Override
        protected void onPause() {
            super.onPause();
            sensorManager.unregisterListener(this);
        }
    }
           sensor example end
     */


    /*    location code---------------2
    //定义位置监听器类
    class MyLocationListener implements LocationListener{
        //当位置发生变化时调用
        @Override
        public void onLocationChanged (Location location){
            //从Location对象获得最新位置信息
            float accuracy = location.getAccuracy();//获得定位精度
            double height = location.getAltitude();//获得高度
            double latitude = location.getLatitude();//获得纬度
            double longtitude = location.getLongitude();//获得经度
            float speed = location.getSpeed();//获得设备移动速度
            float bearing = location.getBearing();//获得设备移动方向
        }

        //位置提供者变为可用时调用
        @Override
        public void onProviderEnabled (String provider){

        }
        //位置信息变为不可用时调用
        @Override
        public void onProviderDisabled (String provider){

        }
        //当位置提供者的状态发生改变时调用，该方法从API-29开始作废，不建议用
        @Override
        public void onStatusChanged (String provider,
                                     int status,
                                     Bundle extras){
        }
    }

    //定义GPS状态监听器类，注：API-24及之后，该接口废弃
    class MyGpsStatusListener implements GpsStatus.Listener{
        @Override
        public void onGpsStatusChanged(int event) {
            switch (event) {
                case GpsStatus.GPS_EVENT_FIRST_FIX://第一次定位
                    break;
                case GpsStatus.GPS_EVENT_SATELLITE_STATUS://卫星状态改变
                    //从LocationManager获取当前GPS状态
                    GpsStatus gpsStatus=mLocationManager.getGpsStatus(null);
                    //获取卫星
                    List<GpsSatellite> satelliteList = gpsStatus.getSatellites();
                    break;
                case GpsStatus.GPS_EVENT_STARTED://定位启动
                    break;
                case GpsStatus.GPS_EVENT_STOPPED://定位结束
                    break;
            }
        }
    }

    //定义导航系统状态监听器类，注：API-24引入GnssStatus类及Callback接口类
    class MyGnnsStatusCallback extends GnssStatus.Callback{
        //启动定位系统后，第一次定位，会回调该方法
        @Override
        public void onFirstFix(int ttffMillis) {
            super.onFirstFix(ttffMillis);
            //参数ttffMillis是从启动到第一次定位经历的时间（毫秒）
        }
        //定位系统开始工作会回调该方法
        @Override
        public void onStarted() {
            super.onStarted();
        }
        //定位系统结束工作会回调该方法
        @Override
        public void onStopped() {
            super.onStopped();
        }
        //周期性报告卫星状态变化
        @Override
        public void onSatelliteStatusChanged(GnssStatus status) {
            super.onSatelliteStatusChanged(status);
            //参数status包括了所有卫星的状态信息
        }
    }

    LocationManager mLocationManager = (LocationManager) Context.getSystemService(Context.LOCATION_SERVICE);
    //创建位置监听器对象实例
    LocationManager mLocationListener = new MyLocationListener();
    MyGpsStatusListener mGpsStatusListener = new MyGpsStatusListener();
    MyGnnsStatusCallback myGnnsStatusCallback = new MyGnnsStatusCallback();

    void GPS() {
        String providerName = LocationManger.getBestProvider(Criteria criteria, boolean enabledOnly);

        //使用名字获取GPS位置提供者对象实例
        mLocationManager.getProvider(LocationManager.GPS_PROVIDER);
        //获得设备上所有的位置提供者
        List<LocationProvider> allProvider = mLocationManager.getAllProviders();
        //获得GPS位置提供者
        LocationProvider gpsProvider = mLocationManager.getProvider(LocationManager.GPS_PROVIDER);
        if(null==gpsProvider){
            //不支持GPS定位
        }

        //注册位置监听器，第二个参数是最小更新时间间隔（毫秒），第三个参数是最小更新距离（米）
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000,10, mLocationListener);
        //注销位置监听器
        mLocationManager.removeUpdates(mLocationListener);

        //添加GPS监听器
        mLocationManager.addGpsStatusListener (mGpsStatusListener);
        //注销GPS监听器
        mLocationManager.removeGpsStatusListener(mGpsStatusListener);

        //注册Gnss监听器
        mLocationManager.registerGnssStatusCallback(myGnnsStatusCallback);
        //注销Gnss监听器
        mLocationManager.unregisterGnssStatusCallback(myGnnsStatusCallback);

        Criteria ca = new Criteria();
        ca.setAccuracy(Criteria.ACCURACY_FINE);//高精度
        ca.setAltitudeRequired(true);//包含高度信息
        ca.setBearingRequired(true);//包含方位信息
        ca.setSpeedRequired(true);//包含速度信息
        ca.setCostAllowed(true);//允许付费
        ca.setPowerRequirement(Criteria.POWER_HIGH);//高耗电
        //获得复合条件的位置提供者，放在链表中，第二个参数true表示只返回当前可用的提供者
        List<LocationProvider> providerList = mLocationManager.getProviders(ca,true);
    }
    //      location code end
     */

    /*     net code start ------------------------3

    void net() {

        //通过拦截广播，监听网络连接事件
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(connectionReceiver, intentFilter);

        //判断是否有网络连接
        ConnectivityManager mConnectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
            //有网络连接;
            if( mNetworkInfo.getType()==ConnectivityManager.TYPE_MOBILE ){
                //移动网络，获得移动网络信息
                int subType = mNetworkInfo.getSubtype();
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                if (subType == TelephonyManager.NETWORK_TYPE_LTE
                        && !telephonyManager.isNetworkRoaming()) {
                    //4G网络;
                } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS
                        || subType == TelephonyManager.NETWORK_TYPE_HSDPA
                        || subType == TelephonyManager.NETWORK_TYPE_EVDO_0
                        && !telephonyManager.isNetworkRoaming()) {
                    //3G网络，联通的3G为UMTS或HSDPA 电信的3G为EVDO
                } else if (subType == TelephonyManager.NETWORK_TYPE_GPRS
                        || subType == TelephonyManager.NETWORK_TYPE_EDGE
                        || subType == TelephonyManager.NETWORK_TYPE_CDMA
                        && !telephonyManager.isNetworkRoaming()) {
                    //移动和联通的2G为GPRS或EGDE，电信的2G为CDMA
                }
            }else if( mNetworkInfo.getType()==ConnectivityManager.TYPE_WIFI ){
                //WIFI网络，获得WIFI信息
                WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                wifiInfo.getSSID();//获得WIFI的SSID
                wifiInfo.getMacAddress();//获得MAC地址
                wifiInfo.getLinkSpeed();//获得连接速度
            }
        } else {
            //无网络连接；
        }
    }

     */

    ///////////////////////////////////

