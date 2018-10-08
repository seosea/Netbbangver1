package com.example.user.netbbangver1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPagerAdapter extends FragmentPagerAdapter {

    public static final int PAGE_NUMBER = 2;
    public MainPagerAdapter(FragmentManager fm){
        super(fm);
    }


    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new HomeFragment();
                //TODO: 페이지 Fragment들 추가
            case 3:
                return new MyPageFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER; //TODO: 원하는 페이지 수 수정
    }
}
