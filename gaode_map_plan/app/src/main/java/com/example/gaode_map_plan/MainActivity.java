package com.example.gaode_map_plan;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.utils.overlay.MovingPointOverlay;
import com.amap.api.maps.utils.overlay.SmoothMoveMarker;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.amap.api.services.route.WalkStep;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PoiSearch.OnPoiSearchListener, RouteSearch.OnRouteSearchListener, AMap.OnMarkerClickListener {

    private EditText start_location;
    private Button btn_search;
    private EditText end_location;
    private Button btn_plan;
    private MapView map;
    private AMap mAamp;
    private UiSettings mUisettings;
    private RouteSearch mReouteSearch;
    private LatLonPoint mStartPoint, mEndPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //创建路线规划对象，设置路线规划监听器
        mReouteSearch = new RouteSearch(this);
        mReouteSearch.setRouteSearchListener(this);

        map.onCreate(savedInstanceState);
        mAamp = map.getMap();

        //设置地图marker监听器，支持用户选择坐标
        mAamp.setOnMarkerClickListener(this);

        //设置定位功能等代码
        mAamp.getUiSettings().setMyLocationButtonEnabled(true);
        mAamp.setMyLocationEnabled(true);

        //设置定位模式
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);

        mAamp.setMyLocationStyle(myLocationStyle);

        //设置俩个按钮的点击事件
        setClick();
    }

    private void setClick() {
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清除点线面
                mAamp.clear();

                //进行搜索
                PoiSearch.Query query = new PoiSearch.Query(start_location.getText().toString(), "", "无锡");

                //设置每页返回多少条
                query.setPageSize(10);


                //设置查询页面
                query.setPageNum(1);

                PoiSearch poiSearch = new PoiSearch(MainActivity.this, query);

                poiSearch.setOnPoiSearchListener(MainActivity.this);

                poiSearch.searchPOIAsyn();

                //如果目的地不为空，搜索
                if (start_location.getText() != null && start_location.getText().toString().length() > 0) {
                    PoiSearch.Query endQuery = new PoiSearch.Query(end_location.getText().toString(), "", "无锡");
                    query.setPageNum(1);
                    query.setPageSize(10);

                    poiSearch = new PoiSearch(MainActivity.this, endQuery);
                    poiSearch.setOnPoiSearchListener(MainActivity.this);
                    poiSearch.searchPOIAsyn();
                }
            }
        });


        //规划路线
        btn_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (start_location == null || end_location == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    AlertDialog dialog = builder.create();
                    dialog.setTitle("提示");
                    dialog.setMessage("请搜索、并选择出发地和目的地后，再规划路");

                    dialog.setButton(AlertDialog.BUTTON_POSITIVE, "知道了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    dialog.show();
                    return;
                }

                RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(mStartPoint, mEndPoint);

                RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo);

                //开始算路
                mReouteSearch.calculateWalkRouteAsyn(query);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        map.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        map.onDestroy();
    }


    private void initView() {
        start_location = (EditText) findViewById(R.id.start_location);
        btn_search = (Button) findViewById(R.id.btn_search);
        end_location = (EditText) findViewById(R.id.end_location);
        btn_plan = (Button) findViewById(R.id.btn_plan);
        map = (MapView) findViewById(R.id.map);
    }


    @Override
    public boolean onMarkerClick(final Marker marker) {
        //用户点击地图上的Marker点后调用，设置出发点和目的地的坐标
        final LatLonPoint point = new LatLonPoint(marker.getPosition().latitude,marker.getPosition().longitude);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        AlertDialog dialog = builder.create();

        dialog.setTitle("提示");

        dialog.setMessage("设置路线的起始地和目的地");

        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "设置为目的地", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mEndPoint = point;
                marker.hideInfoWindow();
            }
        });

        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "设置为出发地", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mStartPoint = point;
                marker.hideInfoWindow();
            }
        });

        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                marker.hideInfoWindow();
            }
        });

        dialog.show();
        return false;
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        //搜索结束后的回调方法
        if (1000 != i) {
            Toast.makeText(this, "查询失败", Toast.LENGTH_SHORT).show();
            return;
        }

        if (poiResult.getQuery().equals(start_location.getText().toString())) {
            //匹配查询的点
            for (PoiItem item : poiResult.getPois()) {
                //绘制点
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(new LatLng(item.getLatLonPoint().getLatitude(), item.getLatLonPoint().getLongitude()));

                //设置点的标题
                markerOptions.title(item.getTitle());

                //设置点的内容
                markerOptions.snippet(item.getSnippet() + "---" + "POI-ID" + item.getPoiId());

                //设置不可拖动
                markerOptions.draggable(false);

                //设置平贴地面
                markerOptions.setFlat(true);

                //加入地图
                Marker marker = mAamp.addMarker(markerOptions);

                //自定义点的图标
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher_background));
            }
        } else if (poiResult.getQuery().equals(end_location.getText().toString())) {
            //匹配查询的点
            for (PoiItem item : poiResult.getPois()) {
                //绘制点
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(new LatLng(item.getLatLonPoint().getLatitude(), item.getLatLonPoint().getLongitude()));

                //设置点的标题
                markerOptions.title(item.getTitle());

                //设置点的内容
                markerOptions.snippet(item.getSnippet() + "---" + "POI-ID" + item.getPoiId());

                //设置不可拖动
                markerOptions.draggable(false);

                //设置平贴地面
                markerOptions.setFlat(true);

                //加入地图
                Marker marker = mAamp.addMarker(markerOptions);

                //自定义点的图标
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher_background));
            }
        }

    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {

    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {
        Toast.makeText(this, (walkRouteResult.getPaths().size()+"个方案"), Toast.LENGTH_SHORT).show();

        List<LatLng> latLngs = new ArrayList<>();
        for (WalkStep step: walkRouteResult.getPaths().get(0).getSteps()) {
            for (LatLonPoint latLonPoint : step.getPolyline()) {
               latLngs.add(new LatLng(latLonPoint.getLatitude(),latLonPoint.getLongitude()));
            }
        }

        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.addAll(latLngs);
        polylineOptions.width(10).color(Color.RED);

        Polyline polyline = mAamp.addPolyline(polylineOptions);

        //模拟跑步过程：使用MovingPointOverlay类进行平滑移动

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLngs.get(0));
        Marker marker = mAamp.addMarker(markerOptions);
        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher_background));

        MovingPointOverlay movingPointOverlay = new MovingPointOverlay(mAamp,marker);
        //设置滑动总时间
        movingPointOverlay.setTotalDuration(5);
        //设置滑动轨迹点集合
        movingPointOverlay.setPoints(latLngs);
        //开始滑动
        movingPointOverlay.startSmoothMove();

        //模拟跑步过程，进行平滑移动
        SmoothMoveMarker smoothMoveMarker = new SmoothMoveMarker(mAamp);
        smoothMoveMarker.setDescriptor(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher_foreground));
        smoothMoveMarker.setPoints(latLngs);
        smoothMoveMarker.setTotalDuration(10);
        smoothMoveMarker.startSmoothMove();
    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }
}
