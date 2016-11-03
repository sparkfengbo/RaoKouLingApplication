package com.raokouling.sparkfengbo.raokoulingapplication.util;

/**
 * Created by fengbo on 2016/11/2.
 */

public class CommonUtils {

    public static <T> T checkNotNull(T reference){
        if(reference == null){
            throw new NullPointerException();
        } else {
            return reference;
        }
    }
}
