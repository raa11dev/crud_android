package com.raa11dev.crudsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DBName = "seno.db";
    protected  static  final String Tabel_name = "biodata";
    //buat database
    public DBHelper(Context context){
        super(context, DBName, null, DATABASE_VERSION);
    }
    //buat table di database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ Tabel_name + " " +
                     "(nim text ,nama text , kelas text, prodi text)";
        db.execSQL(sql);
    }
    //drop tabel
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+Tabel_name+"";
        db.execSQL(sql);
        onCreate(db);
    }
}
