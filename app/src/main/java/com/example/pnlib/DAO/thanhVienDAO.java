package com.example.pnlib.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Database.Dbhelper;
import com.example.pnlib.Entity.thanhVien;

import java.util.ArrayList;

public class thanhVienDAO {
    private SQLiteDatabase database;

    public thanhVienDAO(Context context) {
        Dbhelper dbhelper = new Dbhelper(context);
        database = dbhelper.getWritableDatabase();
    }


    public ArrayList<thanhVien> getAll() {
        String sql = "select * from ThanhVien";
        return getData(sql);
    }

    //get theo id
    public thanhVien getID(String id){
        String sql = "select * from ThanhVien where maTV = ?";
        ArrayList<thanhVien> list = getData(sql, id);
        return list.get(0);
    }


    //getdata nhiều tham số
    @SuppressLint("Range")
    public ArrayList<thanhVien> getData(String sql, String... selectionArgs) {
        ArrayList<thanhVien> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            thanhVien thanhVien = new thanhVien();
            thanhVien.maTV = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTV")));
            thanhVien.hoTen = cursor.getString(cursor.getColumnIndex("hoTen"));
            thanhVien.namSinh =  cursor.getString(cursor.getColumnIndex("namSinh"));
            list.add(thanhVien);
        }
        return list;
    }

    //get data theo id
//    public thanhVien getID(String id){
//        String sql = "SELECT * FROM ThanhVien WHERE maTV=?";
//        ArrayList<thanhVien> list = getData(sql,id);
//        return list.get(0);
//    }

    public boolean insert(thanhVien thanhVien) {
        //viết dữ liệu vào database
        ContentValues values = new ContentValues();
        values.put("hoTen",thanhVien.hoTen);
        values.put("namSinh",thanhVien.namSinh);
        long row = database.insert("ThanhVien", null, values);
        return (row > 0);
    }

    public boolean update(thanhVien thanhVien) {
        ContentValues values = new ContentValues();
        values.put("maTV",thanhVien.maTV);
        values.put("hoTen",thanhVien.hoTen);
        values.put("namSinh",thanhVien.namSinh);
        long row = database.update("ThanhVien", values, "maTV=?", new String[]{String.valueOf(thanhVien.maTV)});
        return (row > 0);
    }

    public boolean delete(String id) {
        long row = database.delete("ThanhVien", "maTV=?", new String[]{id});
        return (row > 0);
    }
}
