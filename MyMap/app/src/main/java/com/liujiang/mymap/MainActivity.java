package com.liujiang.mymap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;


public class MainActivity extends Activity{
    Button getDate;
    ImageButton toCamera;
    ImageButton toTest;
    ImageButton kaifazhe;
    ImageButton putong;
    //用于临时存放经度
    String jingdu_et;
    //用于临时存放纬度
    String weidu_et;
    //用于显示当前位置信息
    TextView address_te;
    //LocationManager：所有的GPS定位相关的服务、对象都由该对象产生
    LocationManager locationManager;
    //高德地图提供的服务类，用于逆地理编码查询
        GeocodeSearch search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取界面中的可视化组件
        //按钮
        getDate = (Button)findViewById(R.id.getDate);
        //进入相机的按钮
        toCamera = (ImageButton)findViewById(R.id.toCamera);
        //进入方向测试的按钮
        toTest = (ImageButton)findViewById(R.id.toTest);
        //进入开发者信息的按钮
        kaifazhe = (ImageButton) findViewById(R.id.kaifazhe);
        //进入普通导航的信息
        putong = (ImageButton) findViewById(R.id.putong);
        //address_et = (EditText)findViewById(R.id.address_et);
        address_te = (TextView)findViewById(R.id.address_et);
        //“获取当前位置”按钮设置监听器
        getDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lng = jingdu_et;
                String lat = weidu_et;
                if(lng.equals("")||lat.equals("")){
                    //泡泡，提示
                    Toast.makeText(getApplicationContext(),"未有GPS信息",Toast.LENGTH_LONG).show();
                }else{
                    //根据经纬度进行异步查询
                    //由于需要访问网络查询地址，所以需要开启新的线程
                    search .getFromLocationAsyn(new RegeocodeQuery(
                            new LatLonPoint(Double.parseDouble(lat)
                                    ,Double.parseDouble(lng))
                            ,20 //半径
                            ,GeocodeSearch.GPS));
                }
            }
        });
        //为进入相机设置监听器
        toCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,MyCamera.class);
                startActivity(intent);
            }
        });
        //为进入方向感应设置监听器
        toTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,Test.class);
                startActivity(intent);
            }
        });
        //为开发者信息添加监听器
        kaifazhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至开发者介绍页面
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,KaifazheActivity.class);
                startActivity(intent);
            }
        });
        //为进入普通导航添加监听器
        putong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至普通导航页面
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });
        //创建LocationManager对象，通过getSystemService方法获取对象
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //从GPS获取最近的定位信息
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        //使用location更新临时存放经纬度的两个String
        updateView(location);
        //设置每10秒获得一次GPS信息
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER
                ,1000,8,new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //当GPS定位信息发生改变时，更新位置
                updateView(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {
                updateView(null);
            }

            @Override
            public void onProviderDisabled(String provider) {
                //当GPS LocationProvider可用时，更新位置
                updateView(locationManager.getLastKnownLocation(provider));
            }
        });

        //创建GeocodeSearch对象
        search = new GeocodeSearch(this);
        //设置解析监听器
        search.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                //查询
                RegeocodeAddress addr = regeocodeResult.getRegeocodeAddress();
                String buildingName = addr.getBuilding();
                if(buildingName.equals("")){
                    //如果没有建筑，则显示街道什么的的
                    address_te.setText(""+addr.getFormatAddress());
                }else{
                    address_te.setText(""+buildingName);
                }
            }
            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                //无地址解析
                //没有地址
            }
        });
    }

    //更新String中的内容
    public void updateView(Location newlocation){
        if(newlocation!=null){
            double j  = newlocation.getLongitude();
            double w = newlocation.getLatitude();
            jingdu_et = Double.valueOf(j).toString();
            weidu_et = Double.valueOf(w).toString();
        }else{
            //如果传入的Location对象为空，则清空,即没有更新
            jingdu_et = "";
            weidu_et = "";
        }
    }
}
