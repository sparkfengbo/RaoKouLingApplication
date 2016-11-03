package com.raokouling.sparkfengbo.raokoulingapplication.base;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.raokouling.sparkfengbo.raokoulingapplication.constants.Constants;

import android.app.Application;
import android.util.Log;

/**
 * Created by fengbo on 2016/11/3.
 */

public class BaseApplication extends Application {

    final String TAG = "BASE_APPLICATION";
    @Override
    public void onCreate() {
        SpeechUtility.createUtility(this, SpeechConstant.APPID + Constants.APPID);
        super.onCreate();

        Log.d(TAG, "Applicaition create");
    }

}
