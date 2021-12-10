package com.raa11dev.crudsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CRUD extends DBHelper {
    public CRUD(Context context){
        super(context);
    }
    //simpan data di database
    public boolean createData(String nim, String nama, String kelas, String prodi){
        ContentValues values = new ContentValues();

        values.put("nim", nim);
        values.put("nama", nama);
        values.put("kelas", kelas);
        values.put("prodi", prodi);

        SQLiteDatabase db = this.getWritableDatabase();
        boolean create = db.insert(Tabel_name, null, values) > 0;
        db.close();
        return create;
    }
    //edit data di database
    public boolean update(String nim, String nama,String kelas,String prodi){
        ContentValues values = new ContentValues();

        values.put("nim", nim);
        values.put("nama", nama);
        values.put("kelas", kelas);
        values.put("prodi", prodi);

        SQLiteDatabase db = this.getWritableDatabase();
        boolean update = db.update(Tabel_name, values, " nim = ? ",new String[]{ nim }) > 0;
        db.close();
        return update;
    }
    //hapus data di database
    public boolean delete(String nim){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean delete = db.delete(Tabel_name, " nim = ? ",new String[]{ nim }) > 0;
        db.close();
        return delete;
    }
    //mengambil semua data di database
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +Tabel_name + " ORDER BY nim asc", null);
        return cursor;
    }
    //mengecek nim yang tidak ada di database
    public Boolean getDataByInput(String Anim) {
        Cursor cursor = null;
        String empName = "";
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            cursor = db.rawQuery("SELECT * FROM " + Tabel_name+ " where nim = ? ", new String[]{Anim});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                empName = cursor.getString(cursor.getColumnIndex("nim"));
            }
            return !empName.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
        }
        return false;
    }
    //mengecek nim yang sudah ada di database
    public Cursor cekData(String nim) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ Tabel_name +" where NIM = ?",new String[] {nim});
        return res;
    }
}

