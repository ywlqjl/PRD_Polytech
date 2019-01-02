package com.liujiang.mymap;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Util.GetLocation;

/**
 * Created by liujiang on 2016/4/13.
 */
public class MyCamera extends Activity{
    //设置程序中的两个按钮
    Button start;
    Button end;
    //显示预览的SurfaceView
    SurfaceView sView;
    Camera camera;
    TextView addraddress_now_et;
    TextView qianfang_et;
    TextView qianfang_et_2;
    TextView qianfang_et_4;
    TextView qianfang_r;
    TextView qianfang_l;
    LocationManager locationManager;
    GeocodeSearch search;
    GeocodeSearch search_1; //第一点
    GeocodeSearch search_2; //第二点
    GeocodeSearch search_4; //第四点
    GeocodeSearch search_r; //右线点
    GeocodeSearch search_l; //左线点
    String jingdu = "0";
    String weidu = "0";
    //定义Sensor管理器
    SensorManager sensorManager;
    float _value = 0.0f;
    GetLocation gl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycamera);
        //获取程序中界面的两个按钮
        start = (Button)findViewById(R.id.start);
        end = (Button)findViewById(R.id.end);
        addraddress_now_et = (TextView)findViewById(R.id.address_now_et);
        qianfang_et = (TextView)findViewById(R.id.qianfang_et);
        qianfang_et_2 = (TextView)findViewById(R.id.qianfang_et_2);
        qianfang_et_4 = (TextView)findViewById(R.id.qianfang_et_4);
        qianfang_r = (TextView)findViewById(R.id.qianfang_r);
        qianfang_l = (TextView)findViewById(R.id.qianfang_l);
        //获取程序界面的SurfaceView
        sView = (SurfaceView)this.findViewById(R.id.sView);
        //设置Surface不需要自己维护缓冲区
        sView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        //设置该组件不会让屏幕自动关闭
        sView.getHolder().setKeepScreenOn(true);
        sView.getHolder().setFixedSize(800,600);
        //加入回调方法
        sView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                //获取Camera对象
                camera = Camera.open();
                try{
                    //设置预览监听
                    camera.setPreviewDisplay(holder);
                    Camera.Parameters parameters = camera.getParameters();
                    camera.setDisplayOrientation(90);
                    parameters.setRotation(90);
                    camera.setParameters(parameters);
                    //启动摄像头预览
                    camera.startPreview();
                }catch(IOException e){
                    e.printStackTrace();
                    camera.release();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if(camera != null){
                    camera.stopPreview();
                    camera.release();
                }
            }
        });


        //有关查询的逻辑=====================================================
        //查询当前地址
        //设置自身点的位置
        //为“开始导航”设置监听器
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lng = jingdu;
                String lat = weidu;
                double lo = Double.parseDouble(lng);
                double la = Double.parseDouble(lat);
                gl = new GetLocation(lo,la,_value);
                //第一点
                //最远点
                String lng_q = Double.valueOf(gl.getSelectLongtitude()).toString();
                String lat_q = Double.valueOf(gl.getSelectLatitude()).toString();
                System.out.println("最远点坐标："+lng_q+","+lat_q);
                //第二点
                //次近点
                String lng_e = Double.valueOf(gl.getSelectLongtitude1()).toString();
                String lat_e = Double.valueOf(gl.getSelectLatitude1()).toString();
                System.out.println("次近点坐标："+lng_e+","+lat_e);
                //第四点
                //最近点
                String lng_e_4 = Double.valueOf(gl.getSelectLongtitude3()).toString();
                String lat_e_4 = Double.valueOf(gl.getSelectLatitude3()).toString();
                System.out.println("最近点坐标："+lng_e_4+","+lat_e_4);
                //右线点
                String lng_r = Double.valueOf(gl.getSelectLongtitude4()).toString();
                String lat_r = Double.valueOf(gl.getSelectLatitude4()).toString();
                System.out.println("右线点坐标："+lng_r+","+lat_r);
                //左线点
                String lng_l = Double.valueOf(gl.getSelectLongtitude5()).toString();
                String lat_l = Double.valueOf(gl.getSelectLatitude5()).toString();
                System.out.println("左线点坐标："+lng_l+","+lat_l);
                if(lng.equals("")||lat.equals("")){
                    Toast.makeText(getApplicationContext(), "未有GPS信息", Toast.LENGTH_LONG).show();
                }else{

                    //根据经纬度进行异步查询
                    search .getFromLocationAsyn(new RegeocodeQuery(
                            new LatLonPoint(Double.parseDouble(lat)
                                    ,Double.parseDouble(lng))
                            ,10 //半径
                            ,GeocodeSearch.GPS));
                    //第二点查询
                    search_2 .getFromLocationAsyn(new RegeocodeQuery(
                            new LatLonPoint(Double.parseDouble(lat_e)
                                    ,Double.parseDouble(lng_e))
                            ,2 //半径
                            ,GeocodeSearch.GPS));
                    //外层点查询
                    search_1 .getFromLocationAsyn(new RegeocodeQuery(
                            new LatLonPoint(Double.parseDouble(lat_q)
                                    ,Double.parseDouble(lng_q))
                            ,2 //半径
                            ,GeocodeSearch.GPS));
                    search_4 .getFromLocationAsyn(new RegeocodeQuery(
                            new LatLonPoint(Double.parseDouble(lat_e_4)
                                    ,Double.parseDouble(lng_e_4))
                            ,2 //半径
                            ,GeocodeSearch.GPS));
                    //右线点查询
                    search_r .getFromLocationAsyn(new RegeocodeQuery(
                            new LatLonPoint(Double.parseDouble(lat_r)
                                    ,Double.parseDouble(lng_r))
                            ,2 //半径
                            ,GeocodeSearch.GPS));
                    //左线点查询
                    search_l .getFromLocationAsyn(new RegeocodeQuery(
                            new LatLonPoint(Double.parseDouble(lat_l)
                                    ,Double.parseDouble(lng_l))
                            ,2 //半径
                            ,GeocodeSearch.GPS));
                }
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCamera.this.finish();
            }
        });
        //创建LocationManager对象
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //从GPS获取最近的定位信息
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        //使用location更新数据
        updateData(location);
        //设置每1秒获得一次GPS信息
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER
                ,1000,8,new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //当GPS定位信息发生改变时，更新位置
                updateData(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {
                updateData(null);
            }

            @Override
            public void onProviderDisabled(String provider) {
                //当GPS LocationProvider可用时，更新位置
                updateData(locationManager.getLastKnownLocation(provider));
            }
        });

        //创建GeocodeSearch对象
        search = new GeocodeSearch(this);
        //设置解析监听器
        search.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                RegeocodeAddress addr = regeocodeResult.getRegeocodeAddress();
                String buildingName = addr.getBuilding();
                if(buildingName.equals("")){
                    addraddress_now_et.setText(""+addr.getFormatAddress());
                }else{
                    addraddress_now_et.setText(""+buildingName);
                }

            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                //无地址解析
            }
        });
        search_1 = new GeocodeSearch(this);
        //设置解析监听器
        //最远点
        search_1.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                RegeocodeAddress addr = regeocodeResult.getRegeocodeAddress();
                String buildingName = addr.getBuilding();
                String where = addr.getProvince()+addr.getCity()+addr.getDistrict();
                qianfang_et.setText("");
                if(buildingName.equals("")){
                    qianfang_et.setText((""+addr.getFormatAddress()).replaceAll(where,""));
                }else{
                    qianfang_et.setText(""+buildingName);
                }
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                //无地址解析
            }
        });
        search_2 = new GeocodeSearch(this);
        //设置解析监听器
        //次近点
        search_2.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                RegeocodeAddress addr = regeocodeResult.getRegeocodeAddress();
                String buildingName = addr.getBuilding();
                String where = addr.getProvince()+addr.getCity()+addr.getDistrict();
                qianfang_et_2.setText("");
                if(buildingName.equals("")){
                    qianfang_et_2.setText((""+addr.getFormatAddress()).replaceAll(where,""));
                }else{
                    qianfang_et_2.setText(""+buildingName);
                }
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                //无地址解析
            }
        });
        search_4 = new GeocodeSearch(this);
        //设置解析监听器
        //最近点
        search_4.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                RegeocodeAddress addr = regeocodeResult.getRegeocodeAddress();
                String buildingName = addr.getBuilding();
                String where = addr.getProvince()+addr.getCity()+addr.getDistrict();
                qianfang_et_4.setText("");
                if(buildingName.equals("")){
                    qianfang_et_4.setText((""+addr.getFormatAddress()).replaceAll(where,""));
                }else{
                    qianfang_et_4.setText(""+buildingName);
                }
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                //无地址解析
            }
        });
        search_r = new GeocodeSearch(this);
        //设置解析监听器
        //最近点
        search_r.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                RegeocodeAddress addr = regeocodeResult.getRegeocodeAddress();
                String buildingName = addr.getBuilding();
                String where = addr.getProvince()+addr.getCity()+addr.getDistrict();
                qianfang_r.setText("");
                if(buildingName.equals("")){
                    qianfang_r.setText((""+addr.getFormatAddress()).replaceAll(where,""));
                }else{
                    qianfang_r.setText(""+buildingName);
                }
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                //无地址解析
            }
        });
        search_l = new GeocodeSearch(this);
        //设置解析监听器
        //最近点
        search_l.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                RegeocodeAddress addr = regeocodeResult.getRegeocodeAddress();
                String buildingName = addr.getBuilding();
                String where = addr.getProvince()+addr.getCity()+addr.getDistrict();
                qianfang_l.setText("");
                if(buildingName.equals("")){
                    qianfang_l.setText((""+addr.getFormatAddress()).replaceAll(where,""));
                }else{
                    qianfang_l.setText(""+buildingName);
                }
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                //无地址解析
            }
        });
        //获取传感器服务
        //获取传感器管理服务
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        //添加监听器
    }

    @Override
    protected void onResume() {
        super.onResume();
//      为感应器添加监听器
        sensorManager.registerListener(new SensorEventListener() {
                                           @Override
                                           public void onSensorChanged(SensorEvent event) {
                                               float value[] = event.values;
                                               _value = value[0];
                                           }

                                           @Override
                                           public void onAccuracyChanged(Sensor sensor, int accuracy) {

                                           }
                                       },
                sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);
    }

    //更新经纬度的方法
    public void updateData(Location newlocation){
        if(newlocation!=null){
            double j  = newlocation.getLongitude();
            double w = newlocation.getLatitude();
            jingdu = Double.valueOf(j).toString();
            weidu = Double.valueOf(w).toString();
        }else{
            //如果传入的Location对象为空，则清空EditTExt
            jingdu = "";
            weidu = "";
        }
    }
    //关于查询正前方位置的地点
}
