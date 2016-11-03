package com.raokouling.sparkfengbo.raokoulingapplication.func.homepage;

import com.raokouling.sparkfengbo.raokoulingapplication.data.bean.EntertainmentObject;
import com.raokouling.sparkfengbo.raokoulingapplication.data.source.EntertainmentDataSource;
import com.raokouling.sparkfengbo.raokoulingapplication.data.source.EntertainmentRepository;

import android.content.Intent;

import java.util.List;

/**
 * Created by fengbo on 2016/11/2.
 */

public class HomePageListPresenter implements HomePageListContract.Presenter {

    private HomePageListContract.View mHomePageListFragment;
    private EntertainmentRepository mEntertainmentRepository;

    public HomePageListPresenter(HomePageListContract.View view, EntertainmentRepository model) {
        mHomePageListFragment = view;
        mEntertainmentRepository = model;

        mHomePageListFragment.setPresenter(this);
    }


    @Override
    public void onResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void loadDataList() {

        mEntertainmentRepository.loadEntertainments(new EntertainmentDataSource.LoadEntertainmentCallback() {
            @Override
            public void onLoaded(List<EntertainmentObject> eos) {
                mHomePageListFragment.showDataList(eos);
            }

            @Override
            public void onDataNotAvailable() {
                mHomePageListFragment.showNoDataError();
            }
        });
    }

    @Override
    public void openDataDetail(EntertainmentObject entertainmentObject) {

    }

    @Override
    public void deleteItem(EntertainmentObject entertainmentObject) {

    }
}


