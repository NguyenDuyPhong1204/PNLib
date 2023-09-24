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

    public ArrayList<sach> getAll() {
        String sql = "select * from Sach";
        return getData(sql);
    }

    //get theo id
    public sach getID(String id){
        String sql = "select * from Sach where maSach = ?";
        ArrayList<sach> list = getData(sql, id);
        return list.get(0);
    }


    @SuppressLint("Range")
    public ArrayList<sach> getData(String sql, String... selectionArgs) {
        ArrayList<sach> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            sach sach = new sach();
            sach.maSach = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSach")));
            sach.maLoai = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai")));
            sach.tenSach = cursor.getString(cursor.getColumnIndex("tenSach"));
            sach.giaThue = Integer.parseInt(cursor.getString(cursor.getColumnIndex("giaThue")));
            list.add(sach);
        }
        return list;
    }


    public boolean insert(sach sach) {
        //viết dữ liệu vào database
        ContentValues values = new ContentValues();
        values.put("tenSach",sach.tenSach);
        values.put("giaThue", sach.giaThue);
        values.put("maLoai", sach.maLoai);
        long row = database.insert("Sach", null, values);
        return (row > 0);
    }

    public boolean update(sach sach) {
        ContentValues values = new ContentValues();
        values.put("maSach", sach.maSach);
        values.put("tenSach",sach.tenSach);
        values.put("giaThue", sach.giaThue);
        values.put("maLoai", sach.maLoai);
        long row = database.update("Sach", values, "maSach=?", new String[]{String.valueOf(sach.maSach)});
        return (row > 0);
    }

    public boolean delete(String id) {
        long row = database.delete("Sach", "maSach=?", new String[]{id});
        return (row > 0);
    }
}
