package com.raokouling.sparkfengbo.raokoulingapplication.func.homepage;

import com.raokouling.sparkfengbo.raokoulingapplication.base.BasePresenter;
import com.raokouling.sparkfengbo.raokoulingapplication.base.BaseView;
import com.raokouling.sparkfengbo.raokoulingapplication.data.bean.EntertainmentObject;
import com.raokouling.sparkfengbo.raokoulingapplication.func.detail.EntertainmentDetailActivity;

import android.content.Intent;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by fengbo on 2016/11/2.
 */

public interface HomePageListContract {

    interface View extends BaseView<HomePageListPresenter>{

        /**
         * 当从网络拉去数据失败时展示提示
         */
        void showNoDataError();

        /**
         * 展示数据到ListView
         * @param eos 从网络或本地拿到的数据，展示给ListView
         */
        void showDataList(List<EntertainmentObject> eos);

        /**
         * 获得新的数据后添加给ListView
         * @param eos 从网络或本地拿到的数据，展示给ListView
         */
        void appendDataList(List<EntertainmentObject> eos);

        /**
         * 点击ListView的某一项启动{@link EntertainmentDetailActivity}
         * @param content
         */
        void showItemDetail(@NonNull String content);

    }


    interface Presenter extends BasePresenter{

        /**
         * 从{@link EntertainmentDetailActivity}返回结果时反馈给{@link HomePageListFragment}
         * @param requestCode
         * @param resultCode
         * @param data
         */
        void onResult(int requestCode, int resultCode, Intent data);

        /**
         * 启动Model，从网络或本地获得数据
         */
        void loadDataList();

        /**
         * 打开对象的详细信息，详细信息由Model获取
         * @param entertainmentObject 要打开的娱乐数据的对象
         */
        void openDataDetail(@NonNull EntertainmentObject entertainmentObject);

        /**
         * 将对象从网络或本地删除，由Model完成
         * @param entertainmentObject
         */
        void deleteItem(@NonNull EntertainmentObject entertainmentObject);

    }


}
