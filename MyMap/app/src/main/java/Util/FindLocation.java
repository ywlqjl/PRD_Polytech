package Util;

import java.util.ArrayList;

/**
 * Created by liujiang on 2016/5/31.
 * 用于求出经纬度的变化量
 */
public class FindLocation {
    //属性
    //自身位置的经纬度
    private double longitude;   //经度
    private double latitude;    //纬度
    //方向
    private float value;
    //getter、setter方法
    public void setLongitude(double longitude){
        this.longitude = longitude;
    }
    public double getLongitude(){
        return this.longitude;
    }
    public void setLatitude(double latitude){
        this.latitude = latitude;
    }
    public double getLatitude(){
        return this.latitude;
    }
    public void setValue(float value){
        this.value = value;
    }
    public float getValue(){
        return this.value;
    }
    //构造方法

    public FindLocation(double longitude,double latitude,float value){
        this.setLongitude(longitude);
        this.setLatitude(latitude);
        this.setValue(value);
    }
    //描绘出一条直线
    //该直线上有20个点，每个点之间的距离在经纬度中大约是0.00005
    //第一个点
    //斜线长5米
    //对边长 = 5 * Math.sin(value*Math.PI/180)
    //领边长 = 5 * Math.cos(value*Math.PI/180)
    //经度增大值
    //纬度
    //变化经度
    public double lng_1(int i){
        //获取方向
        float value = getValue();//即角度
        //实际变化经度
        return (i * Math.sin(value*Math.PI/180))/(111000*Math.cos(latitude*Math.PI/180));
    }
    //变化纬度
    public double lat_1(int i){
        //获取方向
        float value = getValue();//即角度
        //纬度增加度数
        //领边长/111000
        //实际变化纬度
        return (i * Math.cos(value*Math.PI/180))/111000;
    }
    //获取经度变化的结果集
    public ArrayList getLngs(){
        ArrayList lngs = new ArrayList();
        for(int i=0;i<=100;i=i+20){
            lngs.add(lng_1(i));
        }
        return lngs;
    }
    //获取纬度变化的结果集
    public ArrayList getLats(){
        ArrayList lats = new ArrayList();
        for(int i=0;i<=100;i=i+20){
            lats.add(lat_1(i));
        }
        return lats;
    }
    //寻找正确地址的经度集合
    public ArrayList getNowLngs(){
        ArrayList nowLngs = new ArrayList();
        ArrayList lngs = getLngs();
        if(0<value&&value<=90){
            for(Object lng:lngs){

               double nowLng =  longitude + (Double)lng;
               nowLngs.add(nowLng);
            }
            return nowLngs;
        }else if(90<value&&value<=180){
            for(Object lng:lngs){

                double nowLng =  longitude + (Double)lng;
                nowLngs.add(nowLng);
            }
            return nowLngs;
        }else if(180<value&&value<=270){
            for(Object lng:lngs){

                double nowLng =  longitude - (Double)lng;
                nowLngs.add(nowLng);
            }
            return nowLngs;
        }else{
            for(Object lng:lngs){

                double nowLng =  longitude - (Double)lng;
                nowLngs.add(nowLng);
            }
            return nowLngs;
        }
    }
    //寻找正确地址的纬度集合
    public ArrayList getNowLats(){
        ArrayList nowLats = new ArrayList();
        ArrayList lats = getLats();
        if(0<value&&value<=90){
            for(Object lat:lats){
                double nowLat =  latitude + (Double)lat;
                nowLats.add(nowLat);
            }
            return nowLats;
        }else if(90<value&&value<=180){
            for(Object lat:lats){
                double nowLat =  latitude - (Double)lat;
                nowLats.add(nowLat);
            }
            return nowLats;
        }else if(180<value&&value<=270){
            for(Object lat:lats){
                double nowLat =  latitude - (Double)lat;
                nowLats.add(nowLat);
            }
            return nowLats;
        }else{
            for(Object lat:lats){
                double nowLat =  latitude + (Double)lat;
                nowLats.add(nowLat);
            }
            return nowLats;
        }
    }
}
