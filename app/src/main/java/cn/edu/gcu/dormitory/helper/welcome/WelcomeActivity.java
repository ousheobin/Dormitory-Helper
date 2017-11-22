package cn.edu.gcu.dormitory.helper.welcome;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gcu.dormitory.helper.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        List<Fragment> pages=new ArrayList<Fragment>();
        ViewPager viewPager = (ViewPager)findViewById(R.id.welcome_pager);
        FirstWelcomeFragment firstWelcomeFragment = new FirstWelcomeFragment();
        SecondWelcomeFragment secondWelcomeFragment = new SecondWelcomeFragment();
        ThirdWelcomeFragment thirdWelcomeFragment = new ThirdWelcomeFragment();
        pages.add(firstWelcomeFragment);
        pages.add(secondWelcomeFragment);
        pages.add(thirdWelcomeFragment);
        MyPagerAdapter adapter =new MyPagerAdapter(getSupportFragmentManager(),pages);
        viewPager.setAdapter(adapter);
    }
}
