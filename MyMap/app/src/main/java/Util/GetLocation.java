package Util;

/**
 * Created by liujiang on 2016/5/6.
 * 这个类是一个工具类，用于获取当前的位置经纬度以及散布点的经纬度
 * 属性：
 * 1.自身位置的经纬度；
 * 2.方向
 * 方法：
 * 构造方法：获取自身位置的经纬度,以及当前的方向
 *  其中第四点是最近点，第二点是次近点，第三点是次远点，第一点是最远点
 *
 */
public class GetLocation {
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

    public GetLocation(double longitude,double latitude,float value){
        this.setLongitude(longitude);
        this.setLatitude(latitude);
        this.setValue(value);
    }
    //依据方向获取点的数据
    //经度
    public double getSelectLongtitude(){
        //获取方向
        float value = getValue();
        double change = 0.0;
        if(0<value&&value<=90){
            value = value;
            change = (300 * Math.sin(value*Math.PI/180))/(111000*Math.cos(latitude*Math.PI/180));
        }else if(90<value&&value<=180){
            value = value - 90.0f;
            change = (300 * Math.cos(value*Math.PI/180))/111000;
        }else if(180<value&&value<=270){
            value = value - 180.0f;
            change = (300 * Math.sin(value*Math.PI/180))/(111000*Math.cos(latitude*Math.PI/180));
        }else{
            value = value - 270.0f;
            change = (300 * Math.cos(value*Math.PI/180))/111000;
        }
        double selectLongtitude = 0.0;
        float value_1 = getValue();
        if(0<value_1&&value_1<=90){
            selectLongtitude = this.longitude + change;
            return selectLongtitude;
        }else if(90<value_1&&value_1<=180){
            selectLongtitude = this.longitude + change;
            return selectLongtitude;
        }else if(180<value_1&&value_1<=270){
            selectLongtitude = this.longitude - change;
            return selectLongtitude;
        }else{
            selectLongtitude = this.longitude - change;
            return selectLongtitude;
        }

    }
    //纬度
    public double getSelectLatitude(){
        //获取方向
        float value = getValue();
        double selectLatitude = 0.0;
        double change = 0.0;
        if(0<value&&value<=90){
            value = value;
            change = (300 * Math.cos(value*Math.PI/180))/111000;
        }else if(90<value&&value<=180){
            value = value - 90.0f;
            change = (300 * Math.sin(value*Math.PI/180))/(111000*Math.cos(latitude*Math.PI/180));
        }else if(180<value&&value<=270){
            value = value - 180.0f;
            change = (300 * Math.cos(value*Math.PI/180))/111000;
        }else{
            value = value - 270.0f;
            change = (300 * Math.sin(value*Math.PI/180))/(111000*Math.cos(latitude*Math.PI/180));
        }
        float value_1 = getValue();
        if(0<value_1&&value_1<=90){
            selectLatitude = this.latitude + change;
            return selectLatitude;
        }else if(90<value_1&&value_1<=180){
            selectLatitude = this.latitude - change;
            return selectLatitude;
        }else if(180<value_1&&value_1<=270){
            selectLatitude = this.latitude - change;
            return selectLatitude;
        }else{
            selectLatitude = this.latitude + change;
            return selectLatitude;
        }
    }

    //第二点
    //经度
    public double getSelectLongtitude1(){
        //获取方向
        float value = getValue();
        double change = 0.0;
        if(0<value&&value<=90){
            value = value;
            change = (200 * Math.sin(value*Math.PI/180))/(111000*Math.cos(latitude*Math.PI/180));
        }else if(90<value&&value<=180){
            value = value - 90.0f;
            change = (200 * Math.cos(value*Math.PI/180))/111000;
        }else if(180<value&&value<=270){
            value = value - 180.0f;
            change = (200 * Math.sin(value*Math.PI/180))/(111000*Math.cos(latitude*Math.PI/180));
        }else{
            value = value - 270.0f;
            change = (200 * Math.cos(value*Math.PI/180))/111000;
        }
        double selectLongtitude = 0.0;
        float value_1 = getValue();
        if(0<value_1&&value_1<=90){
            selectLongtitude = this.longitude + change;
            return selectLongtitude;
        }else if(90<value_1&&value_1<=180){
            selectLongtitude = this.longitude + change;
            return selectLongtitude;
        }else if(180<value_1&&value_1<=270){
            selectLongtitude = this.longitude - change;
            return selectLongtitude;
        }else{
            selectLongtitude = this.longitude - change;
            return selectLongtitude;
        }
    }
    //纬度
    public double getSelectLatitude1(){
        //获取方向
        float value = getValue();
        double selectLatitude = 0.0;
        double change = 0.0;
        if(0<value&&value<=90){
            value = value;
            change = (200 * Math.cos(value*Math.PI/180))/111000;
        }else if(90<value&&value<=180){
            value = value - 90.0f;
            change = (200 * Math.sin(value*Math.PI/180))/(111000*Math.cos(latitude*Math.PI/180));
        }else if(180<value&&value<=270){
            value = value - 180.0f;
            change = (200 * Math.cos(value*Math.PI/180))/111000;
        }else{
            value = value - 270.0f;
            change = (200 * Math.sin(value*Math.PI/180))/(111000*Math.cos(latitude*Math.PI/180));
        }
        float value_1 = getValue();
        if(0<value_1&&value_1<=90){
            selectLatitude = this.latitude + change;
            return selectLatitude;
        }else if(90<value_1&&value_1<=180){
            selectLatitude = this.latitude - change;
            return selectLatitude;
        }else if(180<value_1&&value_1<=270){
            selectLatitude = this.latitude - change;
            return selectLatitude;
        }else{
            selectLatitude = this.latitude + change;
            return selectLatitude;
        }
    }
    //第四点
    //经度
    public double getSelectLongtitude3(){
        //获取方向
        float value = getValue();
        double change = 0.0;
        if(0<value&&value<=90){
            value = value;
            change = (25 * Math.sin(value*Math.PI/180))/(111000*Math.cos(latitude*Math.PI/180));
        }else if(90<value&&value<=180){
            value = value - 90.0f;
            change = (25 * Math.cos(value*Math.PI/180))/111000;
        }else if(180<value&&value<=270){
            value = value - 180.0f;
            change = (25 * Math.sin(value*Math.PI/180))/(111000*Math.cos(latitude*Math.PI/180));
        }else{
            value = value - 270.0f;
            change = (25 * Math.cos(value*Math.PI/180))/111000;
        }
        double selectLongtitude = 0.0;
        float value_1 = getValue();
        if(0<value_1&&value_1<=90){
            selectLongtitude = this.longitude + change;
            return selectLongtitude;
        }else if(90<value_1&&value_1<=180){
            selectLongtitude = this.longitude + change;
            return selectLongtitude;
        }else if(180<value_1&&value_1<=270){
            selectLongtitude = this.longitude - change;
            return selectLongtitude;
        }else{
            selectLongtitude = this.longitude - change;
            return selectLongtitude;
        }
    }
    //纬度
    public double getSelectLatitude3(){
        //获取方向
        float value = getValue();
        double selectLatitude = 0.0;
        double change = 0.0;
        if(0<value&&value<=90){
            value = value;
            change = (25 * Math.cos(value*Math.PI/180))/111000;
        }else if(90<value&&value<=180){
            value = value - 90.0f;
            change = (25 * Math.sin(value*Math.PI/180))/(111000*Math.cos(latitude*Math.PI/180));
        }else if(180<value&&value<=270){
            value = value - 180.0f;
            change = (25 * Math.cos(value*Math.PI/180))/111000;
        }else{
            value = value - 270.0f;
            change = (25 * Math.sin(value*Math.PI/180))/(111000*Math.cos(latitude*Math.PI/180));
        }
        float value_1 = getValue();
        if(0<value_1&&value_1<=90){
            selectLatitude = this.latitude + change;
            return selectLatitude;
        }else if(90<value_1&&value_1<=180){
            selectLatitude = this.latitude - change;
            return selectLatitude;
        }else if(180<value_1&&value_1<=270){
            selectLatitude = this.latitude - change;
            return selectLatitude;
        }else{
            selectLatitude = this.latitude + change;
            return selectLatitude;
        }
    }
    //第五点 右线
    public double getSelectLongtitude4(){

        //获取方向
        float value = getValue();
        double change = 0.0;
        double selectLongtitude = 0.0;
        if(345<value&&value<=360){
            value = value + 15.0f - 360.0f;
        }else{
            value = value + 15.0f;
        }
        if(0<value&&value<=90){
            value = value;
            change = (200 * Math.sin(value*Math.PI/180))/(111000*Math.cos(latitude*Math.PI/180));
        }else if(90<value&&value<=180){
            value = value - 90.0f;
            change = (200 * Math.cos(value*Math.PI/180))/111000;
        }else if(180<value&&value<=270){
            value = value - 180.0f;
            change = (200 * Math.sin(value*Math.PI/180))/(111000*Math.cos(latitude*Math.PI/180));
        }else{
            value = value - 270.0f;
            change = (200 * Math.cos(value*Math.PI/180))/111000;
        }
        float value_1 = getValue();
        if(0<value_1&&value_1<=90){
            selectLongtitude = this.longitude + change;
            return selectLongtitude;
        }else if(90<value_1&&value_1<=180){
            selectLongtitude = this.longitude + change;
            return selectLongtitude;
        }else if(180<value_1&&value_1<=270){
            selectLongtitude = this.longitude - change;
            return selectLongtitude;
        }else{
            selectLongtitude = this.longitude - change;
            return selectLongtitude;
        }
    }
    public double getSelectLatitude4(){
        //获取方向
        float value = getValue();
        double selectLatitude = 0.0;
        double change = 0.0;
        if(345<value&&value<=360){
            value = value + 15.0f - 360.0f;
        }else{
            value = value + 15.0f;
        }
        if(0<value&&value<=90){
            value = value;
            change = (200 * Math.cos(value*Math.PI/180))/111000;
        }else if(90<value&&value<=180){
            value = value - 90.0f;
            change = (200 * Math.sin(value*Math.PI/180))/(111000*Math.cos(latitude*Math.PI/180));
        }else if(180<value&&value<=270){
            value = value - 180.0f;
            change = (200 * Math.cos(value*Math.PI/180))/111000;
        }else{
            value = value - 270.0f;
            change = (200 * Math.sin(value*Math.PI/180))/(111000*Math.cos(latitude*Math.PI/180));
        }
        float value_1 = getValue();
        if(0<value_1&&value_1<=90){
            selectLatitude = this.latitude + change;
            return selectLatitude;
        }else if(90<value_1&&value_1<=180){
            selectLatitude = this.latitude - change;
            return selectLatitude;
        }else if(180<value_1&&value_1<=270){
            selectLatitude = this.latitude - change;
            return selectLatitude;
        }else{
            selectLatitude = this.latitude + change;
            return selectLatitude;
        }
    }
    //第六点 左点
    public double getSelectLongtitude5(){
        //获取方向
        float value = getValue();
        double selectLongtitude = 0.0;
        double change = 0.0;
        if(0<value&&value<15){
            value = value - 15.0f + 360.0f;
        }else{
            value = value - 15.0f;
        }
        if(0<value&&value<=90){
            value = value;
            change = (200 * Math.sin(value*Math.PI/180))/(111000*Math.cos(latitude*Math.PI/180));
        }else if(90<value&&value<=180){
            value = value - 90.0f;
            change = (200 * Math.cos(value*Math.PI/180))/111000;
        }else if(180<value&&value<=270){
            value = value - 180.0f;
            change = (200 * Math.sin(value*Math.PI/180))/(111000*Math.cos(latitude*Math.PI/180));
        }else{
            value = value - 270.0f;
            change = (200 * Math.cos(value*Math.PI/180))/111000;
        }
        float value_1 = getValue();
        if(0<value_1&&value_1<=90){
            selectLongtitude = this.longitude + change;
            return selectLongtitude;
        }else if(90<value_1&&value_1<=180){
            selectLongtitude = this.longitude + change;
            return selectLongtitude;
        }else if(180<value_1&&value_1<=270){
            selectLongtitude = this.longitude - change;
            return selectLongtitude;
        }else{
            selectLongtitude = this.longitude - change;
            return selectLongtitude;
        }
    }
    public double getSelectLatitude5(){
        //获取方向
        float value = getValue();
        double selectLatitude = 0.0;
        double change = 0.0;
        if(0<value&&value<15){
            value = value - 15.0f + 360.0f;
        }else{
            value = value - 15.0f;
        }
        if(0<value&&value<=90){
            value = value;
            change = (200 * Math.cos(value*Math.PI/180))/111000;
        }else if(90<value&&value<=180){
            value = value - 90.0f;
            change = (200 * Math.sin(value*Math.PI/180))/(111000*Math.cos(latitude*Math.PI/180));
        }else if(180<value&&value<=270){
            value = value - 180.0f;
            change = (200 * Math.cos(value*Math.PI/180))/111000;
        }else{
            value = value - 270.0f;
            change = (200 * Math.sin(value*Math.PI/180))/(111000*Math.cos(latitude*Math.PI/180));
        }
        float value_1 = getValue();
        if(0<value_1&&value_1<=90){
            selectLatitude = this.latitude + change;
            return selectLatitude;
        }else if(90<value_1&&value_1<=180){
            selectLatitude = this.latitude - change;
            return selectLatitude;
        }else if(180<value_1&&value_1<=270){
            selectLatitude = this.latitude - change;
            return selectLatitude;
        }else{
            selectLatitude = this.latitude + change;
            return selectLatitude;
        }
    }
}
