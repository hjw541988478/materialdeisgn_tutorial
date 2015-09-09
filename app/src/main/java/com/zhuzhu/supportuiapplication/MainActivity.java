package com.zhuzhu.supportuiapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.id_tabs)
    TabLayout tabs;

    @Bind(R.id.id_toolbar)
    Toolbar toolbar;

    @Bind(R.id.id_pager)
    ViewPager pager;

    @Bind(R.id.id_fab)
    FloatingActionButton fab;

    @Bind(R.id.id_drawer_nav_view)
    NavigationView navView;

    @Bind(R.id.id_drawer_layout)
    DrawerLayout navDrawer;

    @OnClick(R.id.id_fab)
    void onFabClick() {
        Snackbar.make(fab, "hey , it's SnackBar", Snackbar.LENGTH_SHORT).setAction("Dismiss", null).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_menu);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setCheckable(true);
                navDrawer.closeDrawers();
                return true;
            }
        });


        pager.setAdapter(new ListPagerAdapter(getSupportFragmentManager(), initData()));
        tabs.setupWithViewPager(pager);
    }

    public List<List<String>> initData() {
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<String> tmpList = new ArrayList<>();
            for (int j = 0; j < 10; j++)
                tmpList.add("item" + j);
            result.add(tmpList);
        }
        return result;
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
        if (id == android.R.id.home) {
            navDrawer.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
