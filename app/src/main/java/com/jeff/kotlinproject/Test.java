package com.jeff.kotlinproject;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;

import com.bumptech.glide.load.Encoder;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by .F on 2017/7/12.
 */

public class Test extends Service {

    private IBinder myBinder,bBinder;

    private Random mGenerator;

    private final String TAG = "MyBindService";

    public class MyBinder extends Binder {
        Test getService() {
            return Test.this;
        }
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        myBinder = new MyBinder();
        mGenerator = new Random();
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return myBinder;
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.e(TAG, "onRebind");
        super.onRebind(intent);
    }

    public int getRandomNumber() {
        Integer k = 10;


        return mGenerator.nextInt(100);

    }







}
