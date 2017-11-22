package cn.edu.gcu.dormitory.helper.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.gcu.dormitory.helper.database.DatabaseHelper;
import cn.edu.gcu.dormitory.helper.pojo.NoteBean;

/**
 * Created by ousheobin on 2017/9/12.
 */

public class DutyDao {


    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public DutyDao(Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<NoteBean> getNotes(){
        String sql = "SELECT * FROM tb_notes ORDER BY f_id DESC;";
        List<NoteBean> res = new ArrayList<NoteBean>();
        Cursor cursor = db.rawQuery(sql,new String[]{});
        while(cursor.moveToNext()){
            NoteBean noteBean = new NoteBean();
            noteBean.setTitle(cursor.getString(cursor.getColumnIndex("f_title")));
            noteBean.setDate(cursor.getString(cursor.getColumnIndex("f_time")));
            noteBean.setContent(cursor.getString(cursor.getColumnIndex("f_content")));
            noteBean.setId(cursor.getString(cursor.getColumnIndex("f_id")));
            res.add(noteBean);
        }
        return res;

    }

    public void addNotes(String title,String content){
        SimpleDateFormat sdp = new SimpleDateFormat("yyyy年MM月dd日");
        String sql = "INSERT INTO tb_notes(f_title,f_content,f_time) VALUES(?,?,?);";
        db.beginTransaction();  //开始事务
        try {
            db.execSQL(sql,new String[]{title,content,sdp.format(new Date())});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void deleteNotes(String id){
        String sql = "DELETE FROM tb_notes WHERE f_id=?;";
        db.beginTransaction();  //开始事务
        try {
            db.execSQL(sql,new String[]{id});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

}
