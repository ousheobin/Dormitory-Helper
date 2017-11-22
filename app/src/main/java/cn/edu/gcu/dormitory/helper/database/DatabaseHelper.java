package cn.edu.gcu.dormitory.helper.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ousheobin on 2017/9/12.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dormitory_helper.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        //CursorFactory设置为null,使用默认值
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("create table","Do create");
        String create_option_table = "CREATE TABLE tb_options(f_option_name varchar(255) PRIMARY KEY,f_option_value VARCHAR(255));";
        String create_note_table = "CREATE TABLE tb_notes(f_id INTEGER PRIMARY KEY AUTOINCREMENT,f_content LONGTEXT,f_title VARCHAR(255),f_time VARCHAR(255));";
        String create_phone_table = "CREATE TABLE tb_phone(f_id INTEGER PRIMARY KEY AUTOINCREMENT,f_name VARCHAR(255),f_phone VARCHAR(255));";
        db.execSQL(create_option_table);
        db.execSQL(create_note_table);
        db.execSQL(create_phone_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.i("On SQLite Upgrade","Do nothing");
    }

}
