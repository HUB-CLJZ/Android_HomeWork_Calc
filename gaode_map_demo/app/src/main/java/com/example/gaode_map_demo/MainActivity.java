package com.example.gaode_map_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 1.获取本地的SHA1码
 * 帖子：http://lbsyun.baidu.com/index.php?title=android-locsdk/guide/key
 *
 * 2.下载相关jar包，放到libs目录下，并加入工程
 *
 * 3.在project non-source file视图下的src下的main目录创建jniLibs目录，赋值所需文件
 *
 */
public class MainActivity extends AppCompatActivity implements PoiSearch.OnPoiSearchListener {
    private EditText et_search;
    private Button btn_search;
    private MapView mMapView;
    private AMap mAmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化
        mMapView = findViewById(R.id.gaode_map);
        mMapView.onCreate(savedInstanceState);
        mAmap = mMapView.getMap();
        initView();

        //设置地图可见，并定位到当前位置
        //setViewShow();

        //设置交互组件
        //setInteraction();

        //定位到指定位置,
        //setLocation();


        //对显示区域截图
        //setCutImg();

        //绘制标记点
        //drawPoint();

        //绘制线
        //drawLine();

        //绘制平面
        //drawPlan();

        //搜索地点
        searchLocation();

    }

    private void searchLocation() {
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清除绘制的所有点，面，线
                mAmap.clear();
                PoiSearch.Query query = new PoiSearch.Query(et_search.getText().toString(), "", "无锡");

                //设置每页返回多少条数据
                query.setPageSize(10);

                //设置查询页面
                query.setPageNum(1);

                //进行查询
                PoiSearch poiSearch = new PoiSearch(MainActivity.this,query);
                poiSearch.setOnPoiSearchListener(MainActivity.this);
                poiSearch.searchPOIAsyn();
            }
        });
    }

    private void drawPlan() {
        LatLng latLng = new LatLng(31.60, 120.29);

        Circle circle = mAmap.addCircle(new CircleOptions().center(latLng).radius(50).fillColor(Color.BLUE).strokeColor(Color.RED).strokeWidth(40));

    }

    private void drawLine() {
        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng(31.58, 120.29));
        latLngs.add(new LatLng(31.57, 120.26));
        latLngs.add(new LatLng(31.58, 120.27));

        Polyline polyline = mAmap.addPolyline(new PolylineOptions().addAll(latLngs).width(20).color(Color.RED));


    }

    private void drawPoint() {
        //设置点的配置对象
        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(new LatLng(31.59, 120.29));
        markerOptions.title("无锡");
        markerOptions.snippet("无锡欢迎您");

        //设置可以移动
        markerOptions.draggable(true);

        //设置marker平贴地面
        markerOptions.setFlat(true);

        //加入地图，返回点的对象
        Marker marker = mAmap.addMarker(markerOptions);
        marker.showInfoWindow();
    }

    private void setCutImg() {
        mAmap.getMapScreenShot(new AMap.OnMapScreenShotListener() {
            @Override
            public void onMapScreenShot(Bitmap bitmap) {

            }

            @Override
            public void onMapScreenShot(Bitmap bitmap, int i) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                boolean res = false;
                if (bitmap == null) {
                    return;
                }
                try {
                    FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory() + "/data/" + sdf.format(new Date() + ".png"));
                    res = bitmap.compress(Bitmap.CompressFormat.PNG, 10, fos);
                    try {
                        fos.close();
                        fos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                StringBuffer buffer = new StringBuffer();
                if (res) {
                    buffer.append("截屏成功");
                } else {
                    buffer.append("截屏失败");
                }
                if (i != 0) {
                    buffer.append("地图渲染完成，截屏无网格");
                } else {
                    buffer.append("地图未渲染完成，地图有网格");
                }
                System.out.println(buffer.toString());
            }
        });
    }

    private void setLocation() {
        //参数为，中心的经纬坐标，缩放级别，俯仰角，偏航角
        CameraPosition cameraPosition = new CameraPosition(new LatLng(119.75, 36.38), 15, 10, 0);

        //改变的位置
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

        //也可以直接设置希望展示的地图缩放级别，还有 zoomIn()、zoomOut()方法逐级缩放
        //cameraUpdate = CameraUpdateFactory.zoomTo(17);
        //AMap 类中提供，直接移动过去，不带移动过程动画
        //mAmap.moveCamera(cameraUpdate);

        //AMap 类中提供，带有移动过程的动画，第三个参数是 AMap.CancelableCallback 回调对象
        mAmap.animateCamera(cameraUpdate, 10000, null);

        //设置地图显示区域，先设置东北、西南两个角的经纬度
        LatLng south = new LatLng(118.75, 35.68);
        LatLng north = new LatLng(120.75, 37.38);

        LatLngBounds latLngBounds = new LatLngBounds(south, north);

        mAmap.setMapStatusLimits(latLngBounds);
    }

    private void setInteraction() {

        //定义交互组件设置器
        UiSettings uiSettings = mAmap.getUiSettings();

        //设置显示缩放按钮
        uiSettings.setZoomControlsEnabled(true);

        //设置楼层切换组件可见
        uiSettings.setIndoorSwitchEnabled(true);

        //设置指南针可用
        uiSettings.setCompassEnabled(true);

        //设置所有手势可用
        uiSettings.setAllGesturesEnabled(true);

    }

    /**
     * 设置地图可见
     */
    private void setViewShow() {
        //设置定位样式
        MyLocationStyle myLocationStyle = new MyLocationStyle();

        //设置定位模式为：连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，
        //并且会跟随设备移动。该模式是默认模式
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);

        //定位频率
        myLocationStyle.interval(200);

        //是否显示蓝点，默认不显示
        myLocationStyle.showMyLocation(true);

        //设置定位点外圆周边框的颜色
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));

        //设置定位点圆周内的颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));

        //设置定位蓝点精度圈的边框宽度的方法。
        myLocationStyle.strokeWidth(2.0f);

        //设置定位蓝点的 icon 图标方法，用 BitmapDescriptor 类对象作为参数，该类高德提供。
        //可以使用 com.amap.api.maps.model.BitmapDescriptorFactory 工厂类获得，本处使用内置红色定位点
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        //设置定位蓝点的 Style
        mAmap.setMyLocationStyle(myLocationStyle);

        //设置为true表示启动定位功能，默认是false。
        mAmap.setMyLocationEnabled(true);

        //设置开启室内地图
        mAmap.showIndoorMap(true);

        //夜景地图
        //mAmap.setMapType(AMap.MAP_TYPE_NIGHT);

        //设置实时路况
        mAmap.setTrafficEnabled(true);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        //初始化控件
        et_search = findViewById(R.id.et_search);
        btn_search = findViewById(R.id.btn_search);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    //查询结束后回调的方法
    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        //1000表示查询成功
        if (1000 != i) {
            Toast.makeText(this, "查询失败", Toast.LENGTH_SHORT).show();
            return;
        }

        //匹配查询的点
        for (PoiItem item: poiResult.getPois()) {
            //绘制点
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(item.getLatLonPoint().getLatitude(),item.getLatLonPoint().getLongitude()));

            //设置点的标题
            markerOptions.title(item.getTitle());

            //设置点的内容
            markerOptions.snippet(item.getSnippet()+"---"+"POI-ID"+item.getPoiId());

            //设置不可拖动
            markerOptions.draggable(false);

            //设置平贴地面
            markerOptions.setFlat(true);

            //加入地图
            Marker marker = mAmap.addMarker(markerOptions);

            //自定义点的图标
            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher_background));

        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }


}