package cn.edu.gcu.dormitory.helper.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import cn.edu.gcu.dormitory.helper.database.DatabaseHelper;

/**
 * Created by ousheobin on 2017/9/12.
 */

public class OptionsDao {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public OptionsDao(Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean getFirstUseStatus(){
        String sql = "SELECT f_option_value FROM tb_options WHERE f_option_name = ?";
        Cursor cursor = db.rawQuery(sql,new String[]{"not_first_use"});
        String status = null;
        while(cursor.moveToNext()){
            status = cursor.getString(cursor.getColumnIndex("f_option_value"));
            break;
        }
        if(status!=null && status.equals("yes")){
            return true;
        }else{
            return false;
        }
    }

    public void updateFirstUseStatus(){
        String querySql = "SELECT f_option_value FROM tb_options WHERE f_option_name = ?";
        Cursor cursor = db.rawQuery(querySql,new String[]{"not_first_use"});
        String updateSql = null;
        if(cursor.getCount()==0){
            updateSql = "INSERT INTO tb_options(f_option_name,f_option_value) VALUES('not_first_use','yes');";
        }else{
            updateSql = "UPDATE tb_options SET f_option_value = 'yes' WHERE f_option_name ='not_first_use';";
        }
        db.beginTransaction();  //开始事务
        try {
            db.execSQL(updateSql);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void addDataProtectPassword(String password){
        String sql = "INSERT INTO tb_options(f_option_name,f_option_value) VALUES(?,?);";
        db.beginTransaction();  //开始事务
        try {
            db.execSQL(sql,new String[]{"data_protect_password",password});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void updatePassword(String password){
        String sql = "UPDATE tb_options SET f_option_value = ? WHERE f_option_name = ?;";
        db.beginTransaction();  //开始事务
        try {
            db.execSQL(sql,new String[]{password,"data_protect_password"});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public String getPassword(){
        String sql = "SELECT f_option_value FROM tb_options WHERE f_option_name = ?";
        Cursor cursor = db.rawQuery(sql,new String[]{"data_protect_password"});
        String password = null;
        while(cursor.moveToNext()){
            password = cursor.getString(cursor.getColumnIndex("f_option_value"));
            break;
        }
        return password;
    }

    public void deleteAllData(){
        String emptyNotes = "DELETE FROM tb_notes;";
        String emptyPhone = "DELETE FROM tb_phone;";
        db.beginTransaction();  //开始事务
        try {
            db.execSQL(emptyNotes,new String[]{});
            db.execSQL(emptyPhone,new String[]{});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
}
