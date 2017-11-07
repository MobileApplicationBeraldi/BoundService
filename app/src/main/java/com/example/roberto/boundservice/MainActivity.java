package com.example.roberto.boundservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.gesture.GestureOverlayView;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.roberto.boundservice.MyService.MyBinder;

public class MainActivity extends AppCompatActivity {

    MyBinder myBinder;
    Intent intent;
    //Interface with callbacks

    ServiceConnection msc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("DBG:","onServiceConnected");
            myBinder = (MyBinder) service;
         }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this,MyService.class);
        startService(intent);
        bindService(intent, msc, BIND_AUTO_CREATE);
    }
    @Override
    protected void onStop(){
        super.onStop();
        unbindService(msc);
    }

    public void start(View v){
        myBinder.start();
    }
    public void stop(View v){
        myBinder.stop();
        }
    public void pause(View v){  myBinder.pause_resume();}
}
