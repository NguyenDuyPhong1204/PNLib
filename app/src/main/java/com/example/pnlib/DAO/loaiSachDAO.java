package com.example.pnlib.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Database.Dbhelper;
import com.example.pnlib.Entity.loaiSach;

import java.util.ArrayList;

public class loaiSachDAO {
    private SQLiteDatabase database;

    public loaiSachDAO(Context context) {
        Dbhelper dbhelper = new Dbhelper(context);
        database = dbhelper.getWritableDatabase();
    }

//getAll
    public ArrayList<loaiSach> getAll() {
        String sql = "select * from LoaiSach";
        return getData(sql);
    }

    //get theo id
    public loaiSach getID(String id){
        String sql = "select * from LoaiSach where maLoai = ?";
        ArrayList<loaiSach> list = getData(sql,id);
        return list.get(0);
    }

    @SuppressLint("Range")
    public ArrayList<loaiSach> getData(String sql, String...selectionArgs) {
        ArrayList<loaiSach> list = new ArrayList<>();
        //đọc dữ liệu từ database
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            loaiSach loaiSach = new loaiSach();
            loaiSach.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai"))));
            loaiSach.setTenLoai(cursor.getString(cursor.getColumnIndex("tenLoai")));
            list.add(loaiSach);
        }
        return list;
    }

    public long insert(loaiSach loaiSach) {
        //viết dữ liệu vào database
        ContentValues values = new ContentValues();
        values.put("tenLoai", loaiSach.getTenLoai());
        return database.insert("LoaiSach", null, values);
    }

    public long update(loaiSach loaiSach) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", loaiSach.getTenLoai());
        return database.update("LoaiSach", values, "maLoai=?", new String[]{String.valueOf(loaiSach.getMaLoai())});
    }

    public long delete(String id) {
       return database.delete("LoaiSach", "maLoai=?", new String[]{id});
    }
}
