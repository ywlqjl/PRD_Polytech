package com.liujiang.mymap;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by liujiang on 2016/4/14.
 */
//一个有关方向传感器的测试
public class Test extends Activity implements SensorEventListener{
    //定义Sensor管理器
    SensorManager sensorManager;
    SensorEventListener sensorEventListener;
    TextView value_0_et;
    EditText value_1_et;
    EditText value_2_et;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        //获取上边的EditText组件
        value_0_et = (TextView) findViewById(R.id.value_0_et);
        //获取传感器管理服务
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION)==null){
            Toast.makeText(getApplicationContext(), "该手机没有方向感应装置", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onStop() {
        //程序退出时取消注册传感监听器
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    protected void onPause() {
        //程序暂停时取消注册传感器监听器
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float value[] = event.values;
        //获取触发event的传感器类型
        value_0_et.setText(Float.valueOf(value[0]).toString());
    }


}
