package com.example.yanwenli.capteurdirection;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener
{
    private TextView tv_value1;
    private TextView tv_value2;
    private TextView tv_value3;
    private Button btn_openCamera;
    private SensorManager sManager;
    private Sensor mSensorOrientation;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tv_value1 = (TextView) findViewById(R.id.tv_value1);
        tv_value2 = (TextView) findViewById(R.id.tv_value2);
        tv_value3 = (TextView) findViewById(R.id.tv_value3);
        btn_openCamera = findViewById(R.id.btn_open_camera);
        
        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorOrientation = sManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sManager.registerListener(this, mSensorOrientation, SensorManager.SENSOR_DELAY_UI);
        
        btn_openCamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MyCamera.class);
                startActivity(intent);
            }
        });
    }
    
    @Override
    public void onSensorChanged(SensorEvent event)
    {
        tv_value1.setText("方位角：" + (float) (Math.round(event.values[0] * 100)) / 100);
        tv_value2.setText("倾斜角：" + (float) (Math.round(event.values[1] * 100)) / 100);
        tv_value3.setText("滚动角：" + (float) (Math.round(event.values[2] * 100)) / 100);
    }
    
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
    
    }
    
    @Override
    public void onPointerCaptureChanged(boolean hasCapture)
    {
    
    }
}
