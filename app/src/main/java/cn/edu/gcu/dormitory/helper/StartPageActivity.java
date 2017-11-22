package cn.edu.gcu.dormitory.helper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import cn.edu.gcu.dormitory.helper.dao.OptionsDao;
import cn.edu.gcu.dormitory.helper.welcome.WelcomeActivity;

public class StartPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = null;
                OptionsDao optionsDao = new OptionsDao(StartPageActivity.this.getBaseContext());
                if(optionsDao.getFirstUseStatus()){
                    intent = new Intent(getApplicationContext(),LoginActivity.class);
                }else{
                    intent = new Intent(getApplicationContext(),WelcomeActivity.class);
                }
                startActivity(intent); //执行
                finish();
            }
        };
        timer.schedule(task, 1000 * 4);
    }
}
