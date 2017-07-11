package com.chem99.fireworkstesco;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.baidu.mapapi.SDKInitializer;
import com.chem99.fireworkstesco.service.LocationService;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by zongshuo on 2017/7/5.
 */
public class InitApp extends Application {
    public static LocationService locationService;
    public Vibrator mVibrator;
    public static InitApp initApp;
    @Override
    public void onCreate() {
        super.onCreate();
        initApp();
    }



    private void initApp() {
        initApp=this;
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
    }


}
