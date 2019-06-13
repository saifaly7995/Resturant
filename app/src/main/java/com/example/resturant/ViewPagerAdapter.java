package com.example.resturant;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList= new ArrayList<>();
    private final List<String> fragmentTitles= new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position ==0) {
            return new FragmenttMain();
        } else if (position == 1) {
            return new FragmentPizza();
        }
        else if (position == 2) {
            return new Fragmentfastfood();
        } else if (position == 3) {
            return new FragmentSnacks();
        }
        else if (position == 4) {
            return new FragmentDeals();
        }
        else
        {
            return null;
        }


    }

    @Override
    public int getCount() {
        return fragmentTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }

    public  void  Addfragment(Fragment fragment,String Title){
        fragmentList.add(fragment);
        fragmentTitles.add(Title);
    }
}

