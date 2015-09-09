package com.zhuzhu.supportuiapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/8.
 */
public class ListPagerAdapter extends FragmentPagerAdapter {

    List<List<String>> data = new ArrayList<>();
    List<ListFragment> fragments = new ArrayList<>();

    public ListPagerAdapter(FragmentManager fm, List<List<String>> data) {
        super(fm);
        this.data.clear();
        this.data.addAll(data);

        this.fragments.clear();
        for (int i = 0; i < data.size(); i++) {
            ListFragment itemFragment = new ListFragment();
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("data", (ArrayList<String>) data.get(i));
            itemFragment.setArguments(bundle);
            fragments.add(itemFragment);
        }

    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return String.valueOf(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
