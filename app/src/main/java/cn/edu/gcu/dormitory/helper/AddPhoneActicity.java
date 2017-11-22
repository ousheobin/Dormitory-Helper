package cn.edu.gcu.dormitory.helper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gcu.dormitory.helper.dao.PhoneDao;

public class AddPhoneActicity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone_acticity);
        Button addPhoneBtn = (Button) findViewById(R.id.btn_do_add_phone);
        addPhoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.add_new_name);
                EditText phone = (EditText) findViewById(R.id.add_new_phone);
                String nameText = name.getText().toString();
                String phoneText = phone.getText().toString();
                if(nameText!=null && !nameText.isEmpty()){
                    if(phoneText!=null && !phoneText.isEmpty()){
                        PhoneDao phoneDao = new PhoneDao(getBaseContext());
                        phoneDao.addPhone(nameText,phoneText);
                        finish();
                    }else{
                        Toast.makeText(getBaseContext(),"请输入姓名",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getBaseContext(),"请输入姓名",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
