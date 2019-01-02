package com.liujiang.mymap;

/**
 * Created by liujiang on 2016/4/13.
 */
public class ScanUtil {
    //传入的两个double值
    //经度
    private double longitude_my;
    //纬度
    private double latitude_my;
    //关于自身位置经纬度的getter、setter方法
    public void setLongitude_my(double longitude){
        this.longitude_my = longitude;
    }
    public double getLongitude_my(){
        return this.longitude_my;
    }
    public void setLatitude_my(double latitude){
        this.latitude_my = latitude;
    }
    public double getLatitude_my(){
        return this.latitude_my;
    }

    //获取点1的位置信息
    //获取点1的经度
    public double getLongitude_1(double longitude_my){
        double longitude_1 = longitude_my;
        return longitude_1;
    }
    //获取点1的纬度
    public double getLatitude_1(double latitude_my){
        double latitude_1 = latitude_my + 0.001;
        return latitude_1;
    }
    //获取点2的位置信息
    //获取点2的经度
    public double getLongitude_2(double longitude_my){
        double longitude_2 = longitude_my - 0.001;
        return longitude_2;
    }
    //获取点2的纬度
    public double getLatitude_2(double latitude_my){
        double latitude_2 = latitude_my;
        return latitude_2;
    }
    //获取点3的位置信息
    //获取点3的经度
    public double getLongitude_3(double longitude_my){
        double longitude_3 = longitude_my;
        return longitude_3;
    }
    //获取点3的纬度
    public double getLatitude_3(double latitude_my){
        double latitude_3 = latitude_my - 0.001;
        return latitude_3;
    }
    //获取点4的位置信息
    //获取点4的经度
    public double getLongitude_4(double longitude_my){
        double longitude_4 = longitude_my + 0.001;
        return longitude_4;
    }
    //获取点4的纬度
    public double getLatitude_4(double latitude_my){
        double latitude_4 = latitude_my;
        return latitude_4;
    }
    //获取点5的位置信息
    //获取点5的经度
    public double getLongitude_5(double longitude_my){
        double longitude_5 = longitude_my;
        return longitude_5;
    }
    //获取点5的纬度
    public double getLatitude_5(double latitude_my){
        double latitude_5 = latitude_my + 0.0005;
        return latitude_5;
    }
    //获取点6的位置信息
    //获取点6的经度
    public double getLongitude_6(double longitude_my){
        double longitude_6 = longitude_my - 0.0005;
        return longitude_6;
    }
    //获取点6的纬度
    public double getLatitude_6(double latitude_my){
        double latitude_6 = latitude_my;
        return latitude_6;
    }
    //获取点7的位置信息
    //获取点7的经度
    public double getLongitude_7(double longitude_my){
        double longitude_7 = longitude_my + 0.0005;
        return longitude_7;
    }
    //获取点6的纬度
    public double getLatitude_7(double latitude_my){
        double latitude_7 = latitude_my;
        return latitude_7;
    }
    //获取点8的位置信息
    //获取点8的经度
    public double getLongitude_8(double longitude_my){
        double longitude_6 = longitude_my;
        return longitude_6;
    }
    //获取点8的纬度
    public double getLatitude_8(double latitude_my){
        double latitude_6 = latitude_my - 0.0005;
        return latitude_6;
    }
}
