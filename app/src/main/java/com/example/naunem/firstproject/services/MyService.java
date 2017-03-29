package com.example.naunem.firstproject.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.naunem.firstproject.R;

/**
 * Created by naunem on 27/03/2017.
 */

public class MyService extends Service {

    private MediaPlayer mMediaPlayer;
    private final String TAG = "aaaaaa";

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.svbk);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        mMediaPlayer.start();
        Log.d("dddd", "Media Player started!");
        if (!mMediaPlayer.isLooping()) {
            Log.d("aaaa", "Problem in Playing Audio");
        }
        return START_STICKY;
    }
    
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind: ");
        super.onRebind(intent);
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        mMediaPlayer.stop();
        mMediaPlayer.release();
    }
}

