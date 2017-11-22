package cn.edu.gcu.dormitory.helper.welcome;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by JNCHAN on 2017/9/11.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> pages;

    public MyPagerAdapter(FragmentManager fm, List<Fragment> pages){
        super(fm);
        this.pages=pages;
    }

    @Override
    public Fragment getItem(int position){
        return pages.get(position);
    }

    @Override
    public int getCount(){
        return pages.size();
    }
}
