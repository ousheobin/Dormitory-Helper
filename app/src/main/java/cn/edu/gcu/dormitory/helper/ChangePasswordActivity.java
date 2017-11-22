package cn.edu.gcu.dormitory.helper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import cn.edu.gcu.dormitory.helper.dao.OptionsDao;

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Button comfirmReset = (Button)findViewById(R.id.btn_change_password_comfirm);
        comfirmReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newPassword = (EditText)findViewById(R.id.change_password_new);
                EditText repeatPassword = (EditText)findViewById(R.id.change_password_repeat);
                String newPasswordText = newPassword.getText().toString();
                String repeatPasswordText = repeatPassword.getText().toString();
                if(newPasswordText!=null && !newPasswordText.isEmpty()){
                    if(repeatPasswordText!=null && !repeatPasswordText.isEmpty()){
                        if(newPasswordText.equals(repeatPasswordText)){
                            OptionsDao optionsDao = new OptionsDao(getBaseContext());
                            optionsDao.updatePassword(newPasswordText);
                            Toast.makeText(getBaseContext(),"修改成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(getBaseContext(),"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getBaseContext(),"请重复输入密码",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getBaseContext(),"请输入新密码",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
