package cn.edu.gcu.dormitory.helper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import cn.edu.gcu.dormitory.helper.R;
import cn.edu.gcu.dormitory.helper.dao.DutyDao;

public class AddNotificationActivity extends AppCompatActivity {

    private DutyDao dutyDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notification);
        dutyDao = new DutyDao(getBaseContext());
        Button addButton = (Button)findViewById(R.id.btn_do_add_note);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView title = (TextView)findViewById(R.id.add_note_title);
                TextView content = (TextView)findViewById(R.id.add_note_content);
                String titleText = title.getText().toString();
                String contentText = content.getText().toString();
                if(titleText!=null && !titleText.isEmpty()){
                    if(contentText!=null && !contentText.isEmpty()){
                        dutyDao.addNotes(titleText,contentText);
                        finish();
                    }else{
                        Toast.makeText(getBaseContext(),"请输入内容",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getBaseContext(),"请输入标题",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
