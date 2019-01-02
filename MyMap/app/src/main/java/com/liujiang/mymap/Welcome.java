package com.liujiang.mymap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by liujiang on 2016/6/5.
 */
public class Welcome extends Activity{
    private final int TIME = 3000;  //延迟3秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //Handler消息传递
        //在此线程中处理消息
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //自动跳转至下一页面，即MainActivity所对应的布局文件
                //intent A~B
                Intent intent = new Intent(Welcome.this,MainActivity.class);
                intent.setClass(Welcome.this,MainActivity.class);
                startActivity(intent);
                //此Activity结束
                Welcome.this.finish();
            }
        },TIME);

    }
}
