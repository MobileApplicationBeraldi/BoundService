package com.example.roberto.boundservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

/**
 * Created by roberto on 07.11.2015.
 */
public class MyService extends Service {


    MediaPlayer player = null;
    Boolean running=false;

    IBinder mBinder = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mBinder = new MyBinder();
        running = false;
        Log.i("DBG:","Service Created..");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        player.stop();
        player.release();
        Log.i("DBG:","Service Destroyed..");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    //Binder implements IBinder, the interface with methods for calls

    public class MyBinder extends Binder{


        public void start() {
            if (!running){
                player = MediaPlayer.create(getApplicationContext(),R.raw.braincandy);
                player.start();
                running=true;
            }
        };

        public void stop() {
            if (running){
                player.stop();
                running=false;
            }
        };

        public void pause_resume() {
            if (player.isPlaying())
                player.pause();
            else
                player.start();
        }
    }
}
