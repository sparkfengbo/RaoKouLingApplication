package com.raokouling.sparkfengbo.raokoulingapplication.func.homepage;

import com.raokouling.sparkfengbo.raokoulingapplication.R;
import com.raokouling.sparkfengbo.raokoulingapplication.data.source.EntertainmentRepository;
import com.raokouling.sparkfengbo.raokoulingapplication.util.ActivityUtils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        HomePageListFragment homePageListFragment =
                (HomePageListFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if(homePageListFragment == null){
            homePageListFragment = HomePageListFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), homePageListFragment, R.id.contentFrame);
        }

        new HomePageListPresenter(homePageListFragment,
                new EntertainmentRepository());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
