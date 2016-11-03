package com.raokouling.sparkfengbo.raokoulingapplication.data.source;

import com.raokouling.sparkfengbo.raokoulingapplication.data.bean.EntertainmentObject;

import java.util.List;

/**
 * Created by fengbo on 2016/11/2.
 */

public interface EntertainmentDataSource {


    interface LoadEntertainmentCallback {

        void onLoaded(List<EntertainmentObject> eos);

        void onDataNotAvailable();
    }


    void saveEntertainments(List<EntertainmentObject> eos);

    void deleteEntertainments(String id);

    void loadEntertainments(LoadEntertainmentCallback callback);

    void refreshEntertainments();

}
