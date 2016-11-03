package com.raokouling.sparkfengbo.raokoulingapplication.util;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by fengbo on 2016/11/2.
 */

public class ActivityUtils {

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int fragId){

        CommonUtils.checkNotNull(fragmentManager);
        CommonUtils.checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(fragId, fragment);
        transaction.commit();
    }
}
