package cn.edu.gcu.dormitory.helper;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import cn.edu.gcu.dormitory.helper.premission.PremissionType;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private PhoneFragment phoneFragment;
    private MeFragment meFragment;
    private DutyFragment dutyFragment;
    private Fragment[] fragments;
    private int lastShowFragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_nav);
        initFragments();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        Log.d("Item Selected",Boolean.toString(bottomNavigationView.getMenu().getItem(0).isChecked()));
        addNavBarListener();
    }

    private void addNavBarListener(){
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_phone:
                        if (lastShowFragment != 0) {
                            switchFragment(0);
                            lastShowFragment = 0;
                        }
                        return true;
                    case R.id.navigation_duty:
                        if (lastShowFragment != 1) {
                            switchFragment(1);
                            lastShowFragment = 1;
                        }
                        return true;
                    case R.id.navigation_me:
                        if (lastShowFragment != 2) {
                            switchFragment(2);
                            lastShowFragment = 2;
                        }
                        return true;
                    default:
                        Toast.makeText(MainActivity.this.getBaseContext(),"出错了",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    public void switchFragment(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for(Fragment fragment : fragments){
            transaction.hide(fragment);
        }
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.main_page_container, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

    public void initFragments(){
        fragments = new Fragment[3];
        phoneFragment = new PhoneFragment();
        meFragment = new MeFragment();
        dutyFragment = new DutyFragment();
        fragments[0] = phoneFragment;
        fragments[1] = dutyFragment;
        fragments[2] = meFragment;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_page_container, fragments[0])
                .show(fragments[0])
                .commit();
    }

    /**
     * 注册权限申请回调
     * @param requestCode 申请码
     * @param permissions 申请的权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case  PremissionType.REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhone();
                } else {
                    Toast.makeText(getApplicationContext(), "CALL_PHONE Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * 拨号方法
     */
    public void callPhone(){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:13000000000"));
        try{
            startActivity(intent);
        }catch (SecurityException e){
            Toast.makeText(getApplicationContext(),"异常",Toast.LENGTH_LONG).show();
            Log.e("Exception",e.toString());
        }
    }



}
