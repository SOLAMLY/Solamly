package com.example.solamly.solamly.module.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @Author SOLAMLY
 * @Date 2018/9/3 14:31
 * @Description:
 */

public class MySqliteHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "user.db";            //数据库名
    public static final String TABLE_NAME = "user_table";      //数据表名

    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String db_table = "create table " + TABLE_NAME + "("
                + "id integer primary key autoincrement,"
                + "name varchar,"
                + "sex varchar)";
        db.execSQL(db_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
