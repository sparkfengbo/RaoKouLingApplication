package com.raokouling.sparkfengbo.raokoulingapplication.func.detail;

import com.raokouling.sparkfengbo.raokoulingapplication.R;
import com.raokouling.sparkfengbo.raokoulingapplication.util.ActivityUtils;
import com.raokouling.sparkfengbo.raokoulingapplication.util.CommonUtils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EntertainmentDetailActivity extends AppCompatActivity {

    public static final int REQUEST_DETAIL_CODE = 1;
    public static final String EXTRA_DETAIL_CONTENT = "detail_content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment_detail);

        String content = getIntent().getStringExtra(EXTRA_DETAIL_CONTENT);
        CommonUtils.checkNotNull(content);

        EntertainmentDetailFragment entertainmentDetailFragment =
                (EntertainmentDetailFragment) getSupportFragmentManager().
                        findFragmentById(R.id.activity_entertainment_detail);

        if(entertainmentDetailFragment == null){
            entertainmentDetailFragment = EntertainmentDetailFragment.newInstance(content);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), entertainmentDetailFragment, R.id.activity_entertainment_detail);
        }

    }
}
