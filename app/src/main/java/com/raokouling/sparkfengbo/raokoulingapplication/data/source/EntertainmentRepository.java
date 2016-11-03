package com.raokouling.sparkfengbo.raokoulingapplication.data.source;

import com.raokouling.sparkfengbo.raokoulingapplication.data.bean.EntertainmentObject;
import com.raokouling.sparkfengbo.raokoulingapplication.data.source.local.EntertainmentLocalDataSource;
import com.raokouling.sparkfengbo.raokoulingapplication.data.source.remote.EntertainmentRemoteDataSource;

import java.util.List;

/**
 * Created by fengbo on 2016/11/2.
 */

public class EntertainmentRepository implements EntertainmentDataSource{


    private EntertainmentLocalDataSource mLocalDataSource;
    private EntertainmentRemoteDataSource mRemoteDataSource;
    private List<EntertainmentObject> mEntertainmentObjects;

    public EntertainmentRepository(){
        mLocalDataSource = new EntertainmentLocalDataSource();
        mRemoteDataSource = new EntertainmentRemoteDataSource();
    }

    @Override
    public void saveEntertainments(List<EntertainmentObject> eos) {

    }

    @Override
    public void deleteEntertainments(String id) {

    }

    @Override
    public void loadEntertainments(LoadEntertainmentCallback callback) {
        mRemoteDataSource.loadEntertainments(callback);
    }

    @Override
    public void refreshEntertainments() {

    }


}
