package cn.edu.gcu.dormitory.helper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.edu.gcu.dormitory.helper.dao.OptionsDao;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button doRegisterBtn = (Button) findViewById(R.id.btn_register_comfirm);
        doRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText password = (EditText) findViewById(R.id.register_password);
                String passwordText = password.getText().toString();
                if(passwordText!=null&&!passwordText.isEmpty()){
                    OptionsDao optionsDao = new OptionsDao(getBaseContext());
                    optionsDao.addDataProtectPassword(passwordText);
                    optionsDao.updateFirstUseStatus();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getBaseContext(),"请输入密码",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
