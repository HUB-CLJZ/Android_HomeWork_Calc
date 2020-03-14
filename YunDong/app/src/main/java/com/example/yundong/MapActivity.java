package com.example.yundong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MapActivity extends AppCompatActivity {

    private MapView mMapView;
    private AMap mAmap;
    private UiSettings mUiSettings;//定义一个UiSettings对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mMapView = findViewById(R.id.gaode_map);
        mMapView.onCreate(savedInstanceState);
        mAmap = mMapView.getMap();//得到Amap对象
        mAmap.getUiSettings().setMyLocationButtonEnabled(true);
        mAmap.setMyLocationEnabled(true);// 设置为true表示启动定位功能，默认是false。

        MyLocationStyle myLocationStyle = new MyLocationStyle();
        //设置定位模式为：连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。
        //该模式也是默认模式
        //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        //myLocationStyle.interval(2000); //定位频率，连续定位模式下生效，单位为毫秒。
       // myLocationStyle.showMyLocation(true);//是否显示蓝点，false不显示
        //myLocationStyle.strokeColor(Color.RED);//设置定位蓝点精度圆圈的边框颜色的方法。
        //myLocationStyle.radiusFillColor(Color.GRAY);//设置定位蓝点精度圆圈的填充颜色的方法。
        //myLocationStyle.strokeWidth(3.0f);//设置定位蓝点精度圈的边框宽度的方法。
        //设置定位蓝点的icon图标方法，用BitmapDescriptor类对象作为参数，该类高德提供。
        //可以使用com.amap.api.maps.model.BitmapDescriptorFactory工厂类获得，本处使用内置红色定位点
        //myLocationStyle.myLocationIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        mAmap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style

        //mAmap.showIndoorMap(true);//开启室内地图功能

        //mAmap.setMapType(AMap.MAP_TYPE_NIGHT);//夜景地图
        //mAmap.setTrafficEnabled(true);//显示实时路况图层


        findViewById(R.id.button_offmap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //在Activity页面调用startActvity启动离线地图组件
                startActivity(new Intent(MapActivity.this.getApplicationContext(),
                        com.amap.api.maps.offlinemap.OfflineMapActivity.class));
            }
        });

        findViewById(R.id.button_mapscreenshot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAmap.getMapScreenShot(new AMap.OnMapScreenShotListener() {
                    @Override
                    public void onMapScreenShot(Bitmap bitmap) {    }
                    @Override
                    public void onMapScreenShot(Bitmap bitmap, int status) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                        if(null == bitmap) return;
                        try {
                            FileOutputStream fos = new FileOutputStream(
                                    Environment.getExternalStorageDirectory() + "/data/test_"
                                            + sdf.format(new Date()) + ".png");
                            boolean b = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                            try {
                                fos.flush();
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            StringBuffer buffer = new StringBuffer();
                            if (b)  buffer.append("截屏成功 ");
                            else    buffer.append("截屏失败 ");
                            if (status != 0) buffer.append("地图渲染完成，截屏无网格");
                            else buffer.append( "地图未渲染完成，截屏有网格");
                            Toast.makeText(MapActivity.this.getApplicationContext(),buffer.toString(),Toast.LENGTH_SHORT).show();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        findViewById(R.id.button_interactionwithmap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mUiSettings = mAmap.getUiSettings();//实例化UiSettings类对象
                mUiSettings.setZoomControlsEnabled(true);//设置显示缩放按钮
                mUiSettings.setIndoorSwitchEnabled(true);//设置室内地图楼层切换控件是否可见。
                mUiSettings.setCompassEnabled(true);//设置指南针是否可见。
                mUiSettings.setAllGesturesEnabled(true);//设置所有手势是否可用
                mUiSettings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_CENTER);//设置“高德地图”Logo的位置。
                mUiSettings.setMyLocationButtonEnabled(true);//设置定位按钮是否可见。
                mUiSettings.setRotateGesturesEnabled(true);//设置旋转手势是否可用。
                mUiSettings.setScaleControlsEnabled(true);//设置比例尺控件是否可见
                mUiSettings.setScrollGesturesEnabled(true);//设置拖拽手势是否可用。
                mUiSettings.setTiltGesturesEnabled(true);//设置倾斜手势是否可用。
                mUiSettings.setZoomControlsEnabled(true);//设置缩放按钮是否可见
                mUiSettings.setZoomGesturesEnabled(true);//设置双指缩放手势是否可用。
                mUiSettings.setZoomPosition(AMapOptions.ZOOM_POSITION_RIGHT_CENTER);//设置缩放按钮的位置。

            }
        });

        findViewById(R.id.button_maptowuxi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //参数依次是：视角调整区域的中心点坐标、希望调整到的缩放级别、俯仰角0°~45°（与地图垂直时为0）、偏航角 0~360° (正北方为0)
                CameraPosition cameraPosition = new CameraPosition(new LatLng(31.59,120.29),15,10,0);
                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                //也可以直接设置希望展示的地图缩放级别，还有zoomIn()、zoomOut()方法逐级缩放
                //cameraUpdate = CameraUpdateFactory.zoomTo(17);
                //AMap类中提供，直接移动过去，不带移动过程动画
                //mAmap.moveCamera(cameraUpdate);
                //AMap类中提供，带有移动过程的动画，第三个参数是AMap.CancelableCallback回调对象
                mAmap.animateCamera(cameraUpdate,1000,null);

                //设置地图显示区域，先设置东北、西南两个角的经纬度
                LatLng southwestLatLng = new LatLng(30.59, 119.29);
                LatLng northeastLatLng = new LatLng(32.59, 121.29);
                LatLngBounds latLngBounds = new LatLngBounds(southwestLatLng, northeastLatLng);
                mAmap.setMapStatusLimits(latLngBounds);//用Amap类方法设定显示范围

                MarkerOptions markerOption = new MarkerOptions();//创建点配置对象
                markerOption.position(new LatLng(31.59,120.29));//设置点的坐标
                markerOption.title("无锡市");//点标题
                markerOption.snippet("无锡欢迎你！");//点的内容
                markerOption.draggable(true);//设置Marker可拖动;
                markerOption.setFlat(true);//设置marker平贴地图效果
                Marker marker = mAmap.addMarker(markerOption);//加入地图，返回点对象

                List<LatLng> latLngs = new ArrayList<LatLng>();
                latLngs.add(new LatLng(31.58,120.29));
                latLngs.add(new LatLng(31.57,120.26));
                latLngs.add(new LatLng(31.58,120.27));
                Polyline polyline =mAmap.addPolyline(new PolylineOptions().
                        addAll(latLngs).width(10).color(Color.RED));

                LatLng latLng = new LatLng(31.60,120.29);
                Circle circle = mAmap.addCircle(new CircleOptions().
                        center(latLng).
                        radius(100).
                        fillColor(Color.YELLOW).
                        strokeColor(Color.RED).
                        strokeWidth(15));
            }
        });
    }
    //方法必须重写
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }
    //方法必须重写
    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }
    //方法必须重写
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }
    //方法必须重写
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();


        




    }
}
