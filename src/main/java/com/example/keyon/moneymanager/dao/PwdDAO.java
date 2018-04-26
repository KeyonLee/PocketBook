package com.example.keyon.moneymanager.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.keyon.moneymanager.model.Tb_pwd;

public class PwdDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public PwdDAO(Context context){
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 添加密码信息
     *
     * @param tb_pwd
     */
    public void add(Tb_pwd tb_pwd) {
        db.execSQL("insert into tb_pwd (password) values (?)",
                new Object[] { tb_pwd.getPassword() });
    }

    /**
     * 设置密码信息
     *
     * @param tb_pwd
     */
    public void update(Tb_pwd tb_pwd) {
        db.execSQL("update tb_pwd set password = ?",
                new Object[] { tb_pwd.getPassword() });
    }

    /**
     * 查找密码信息
     *
     * @return
     */
    public Tb_pwd find() {
        Cursor cursor = db.rawQuery("select password from tb_pwd", null);
        if (cursor.moveToNext()){
            return new Tb_pwd(cursor.getString(cursor
                    .getColumnIndex("password")));
        }
        cursor.close();
        return null;
    }

    public long getCount() {
        Cursor cursor = db.rawQuery("select count(password) from tb_pwd", null);
        if (cursor.moveToNext()){
            return cursor.getLong(0);
        }
        cursor.close();
        return 0;
    }
}

