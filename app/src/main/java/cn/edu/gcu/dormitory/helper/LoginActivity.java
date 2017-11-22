package cn.edu.gcu.dormitory.helper;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.edu.gcu.dormitory.helper.dao.OptionsDao;

public class LoginActivity extends Activity {

    OptionsDao optionsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        optionsDao = new OptionsDao(LoginActivity.this);
        Button loginButton = (Button)findViewById(R.id.btn_login_comfirm);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText password = (EditText) findViewById(R.id.login_password);
                String passwordText = password.getText().toString();
                if(checkPassword(passwordText)){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public boolean checkPassword(String password){
        if(password!=null && !password.isEmpty()){
            String dbPassword = optionsDao.getPassword();
            if(dbPassword!=null&&dbPassword.equals(password)){
                return true;
            }else{
                Toast.makeText(getBaseContext(),"密码错误，请重试",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getBaseContext(),"请输入密码",Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
