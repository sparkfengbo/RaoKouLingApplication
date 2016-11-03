package com.raokouling.sparkfengbo.raokoulingapplication.func.homepage;

import com.raokouling.sparkfengbo.raokoulingapplication.R;
import com.raokouling.sparkfengbo.raokoulingapplication.data.bean.EntertainmentObject;
import com.raokouling.sparkfengbo.raokoulingapplication.func.detail.EntertainmentDetailActivity;
import com.raokouling.sparkfengbo.raokoulingapplication.util.CommonUtils;
import com.raokouling.sparkfengbo.raokoulingapplication.widget.ScrollChildSwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fengbo on 2016/11/2.
 */

public class HomePageListFragment extends Fragment implements HomePageListContract.View{


    private HomePageListPresenter mPresenter;
    private EntertainmentAdapter mEntertainmentAdapter;
    private TextView mNoDataHint;
    private ListView mListView;

    public static HomePageListFragment newInstance(){
        return new HomePageListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEntertainmentAdapter= new EntertainmentAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_homepage, container, false);

        mListView = (ListView) root.findViewById(R.id.lvEntertainmentObject);
        mListView.setAdapter(mEntertainmentAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EntertainmentObject object = (EntertainmentObject) parent.getItemAtPosition(position);
                showItemDetail(object.getContent());
            }
        });

        mNoDataHint = (TextView) root.findViewById(R.id.tvNoData);

        ScrollChildSwipeRefreshLayout scrollChildSwipeRefreshLayout = (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.scrollLayout);
        scrollChildSwipeRefreshLayout.setScrollUpView(mListView);
        scrollChildSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadDataList();
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadDataList();
    }

    @Override
    public void showNoDataError() {
        mListView.setVisibility(View.GONE);
        mNoDataHint.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDataList(List<EntertainmentObject> eos) {
        mEntertainmentAdapter.resetData(eos);
        mListView.setVisibility(View.VISIBLE);
        mNoDataHint.setVisibility(View.GONE);
    }

    @Override
    public void appendDataList(List<EntertainmentObject> eos) {
        mEntertainmentAdapter.appendData(eos);
        mListView.setVisibility(View.VISIBLE);
        mNoDataHint.setVisibility(View.GONE);
    }

    @Override
    public void showItemDetail(@NonNull String content) {
        Intent intent = new Intent(this.getContext(), EntertainmentDetailActivity.class);
        intent.putExtra(EntertainmentDetailActivity.EXTRA_DETAIL_CONTENT, content);
        startActivityForResult(intent, EntertainmentDetailActivity.REQUEST_DETAIL_CODE);
    }

    @Override
    public void setPresenter(HomePageListPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.onResult(requestCode, requestCode, data);
    }

    private static class EntertainmentAdapter extends BaseAdapter{

        private final int TYPE_COUNT = 1;
        private List<EntertainmentObject> mObjects;

        public void resetData(List<EntertainmentObject> data){
            mObjects = CommonUtils.checkNotNull(data);
            notifyDataSetChanged();
        }

        public void appendData(List<EntertainmentObject> data){
            mObjects.addAll(CommonUtils.checkNotNull(data));
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mObjects == null ? 0 : mObjects.size();
        }

        @Override
        public int getViewTypeCount() {
            return TYPE_COUNT;
        }

        @Override
        public EntertainmentObject getItem(int position) {
            return position >= 0 ? mObjects.get(position) : null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            EntertainmentObject object = getItem(position);
            if(convertView == null || convertView.getTag() == null){
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entertainment, parent, false);
                viewHolder.content = (TextView) convertView.findViewById(R.id.tvItemContent);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //content 是富文本
            String htmlContent = object.getContent();
            viewHolder.content.setText(Html.fromHtml(htmlContent));
            return convertView;
        }

        class ViewHolder{
            TextView content;
        }
    }
}
