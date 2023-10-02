package com.example.pnlib.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Database.Dbhelper;
import com.example.pnlib.Entity.sach;


import java.util.ArrayList;

public class sachDAO {
    private SQLiteDatabase database;

    public sachDAO(Context context) {
        Dbhelper dbhelper = new Dbhelper(context);
        database = dbhelper.getWritableDatabase();
    }


    @SuppressLint("Range")
    public ArrayList<sach> getData(String sql, String... selectionArgs) {
        ArrayList<sach> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            sach sach = new sach();
            sach.setMaSach(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSach"))));
            sach.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai"))));
            sach.setTenSach(cursor.getString(cursor.getColumnIndex("tenSach")));
            sach.setGiaThue(Integer.parseInt((cursor.getString(cursor.getColumnIndex("giaThue")))));
            list.add(sach);
        }
        return list;
    }

    public ArrayList<sach> getAll() {
        String sql = "select * from Sach";
        return getData(sql);
    }

    //get theo id
    public sach getID(String id) {
        String sql = "select * from Sach where maSach= ?";
        ArrayList<sach> list = getData(sql,id);
        return list.get(0);
    }


    public long insert(sach sach) {
        //viết dữ liệu vào database
        ContentValues values = new ContentValues();
        values.put("tenSach", sach.getTenSach());
        values.put("giaThue", sach.getGiaThue());
        values.put("maLoai", String.valueOf(sach.getMaLoai()));
        return database.insert("Sach", null, values);
    }

    public long update(sach sach) {
        ContentValues values = new ContentValues();
//        values.put("maSach", sach.getMaSach());
        values.put("tenSach", sach.getTenSach());
        values.put("giaThue", sach.getGiaThue());
        values.put("maLoai", sach.getMaLoai());
        return database.update("Sach", values, "maSach=?", new String[]{String.valueOf(sach.getMaSach())});
    }

    public long delete(String id) {
       return database.delete("Sach", "maSach=?", new String[]{id});
    }
}
