package cn.edu.gcu.dormitory.helper.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gcu.dormitory.helper.database.DatabaseHelper;
import cn.edu.gcu.dormitory.helper.pojo.PhoneBean;

/**
 * Created by ousheobin on 2017/9/12.
 */

public class PhoneDao {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public PhoneDao(Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<PhoneBean> getPhoneBeans(){
        List<PhoneBean> res = new ArrayList<>();
        String sql = "SELECT * FROM tb_phone;";
        Cursor cursor = db.rawQuery(sql,new String[]{});
        while(cursor.moveToNext()){
            PhoneBean bean = new PhoneBean();
            bean.setPhone(cursor.getString(cursor.getColumnIndex("f_phone")));
            bean.setName(cursor.getString(cursor.getColumnIndex("f_name")));
            bean.setId(cursor.getString(cursor.getColumnIndex("f_id")));
            res.add(bean);
        }
        return res;
    }

    public void addPhone(String name,String phone){
        String sql = "INSERT INTO tb_phone(f_name,f_phone) VALUES(?,?);";
        db.beginTransaction();  //开始事务
        try {
            db.execSQL(sql,new String[]{name,phone});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void deletePhone(String id){
        String sql = "DELETE FROM tb_phone WHERE f_id=?;";
        db.beginTransaction();  //开始事务
        try {
            db.execSQL(sql,new String[]{id});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
}
