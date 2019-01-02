package com.liujiang.mymap;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

/**
 * Created by liujiang on 2016/6/2.
 */
public class MapActivity extends Activity{
    private MapView mapView;
    private AMap aMap;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_putong);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mapView = (MapView) findViewById(R.id.map);
        //回调MapView的onCreate方法
        mapView.onCreate(savedInstanceState);
        init();
        //通过监听器监听GPS提供的定位信息
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                300,8,new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        //使用GPS提供的信息来更新位置
                        updatePosition(location);
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                }
        );
    }
    //更新位置
    private void updatePosition(Location location){
        LatLng pos = new LatLng(location.getLatitude(),location.getLongitude());
        //创建一个设置经纬度的CameraUpdate
        CameraUpdate cu = CameraUpdateFactory.changeLatLng(pos);
        //更新地图的显示区域
        aMap.moveCamera(cu);
        //清除所有Marker等覆盖物
        aMap.clear();
        //创建一个MarkerOptons对象
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(pos);
        //设置MarkerOptions使用自定义图标
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.tag));
        markerOptions.draggable(true);
        //添加MarkerOptions
        Marker marker = aMap.addMarker(markerOptions);
    }
    //初始化AMap对象
    private void init(){
        if(aMap == null){
            aMap = mapView.getMap();
            //创建了一个设置放大级别的CameraUpdate
            CameraUpdate cu = CameraUpdateFactory.zoomTo(15);
            //设置地图的默认放大级别
            aMap.moveCamera(cu);
            //创建一个更改地图倾斜度的CameraUpdate
            CameraUpdate tiltUpdate = CameraUpdateFactory.changeTilt(30);
            //改变地图的倾斜度
            aMap.moveCamera(tiltUpdate);
        }
    }
}
