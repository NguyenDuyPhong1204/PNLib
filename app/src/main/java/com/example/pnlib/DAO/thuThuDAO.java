package com.example.pnlib.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Database.Dbhelper;
import com.example.pnlib.Entity.thanhVien;
import com.example.pnlib.Entity.thuThu;

import java.util.ArrayList;

public class thuThuDAO {
    private SQLiteDatabase database;

    public thuThuDAO(Context context) {
        Dbhelper dbhelper = new Dbhelper(context);
        database = dbhelper.getWritableDatabase();
    }




    //check đăng nhập
    public boolean checkDangNhap(String maTT, String matKhau) {
        Cursor cursor = database.rawQuery("SELECT * FROM ThuThu WHERE maTT = ? AND matKhau = ?", new String[]{maTT, matKhau});
        int row = cursor.getCount();
        return (row > 0);
    }

    //check thủ thư
    public boolean checkThuThu(String maTT) {
        Cursor cursor = database.rawQuery("SELECT * FROM ThuThu WHERE maTT = ?", new String[]{maTT});
        int row = cursor.getCount();
        return (row > 0);
    }

    public ArrayList<thuThu> getAll() {
        String sql = "select * from ThuThu";
        return getData(sql);
    }

    //get theo id
    public thuThu getID(String id){
        String sql = "select * from ThuThu where maTT = ?";
        ArrayList<thuThu> list = getData(sql, id);
        return list.get(0);
    }


    @SuppressLint("Range")
    public ArrayList<thuThu> getData(String sql, String... selectionArgs) {
        ArrayList<thuThu> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            thuThu thuThu = new thuThu();
            thuThu.maTT = cursor.getString(cursor.getColumnIndex("maTT"));
            thuThu.hoTen = cursor.getString(cursor.getColumnIndex("hoTen"));
            thuThu.matKhau = cursor.getString(cursor.getColumnIndex("matKhau"));
            list.add(thuThu);
        }
        return list;
    }

    public boolean insert(thuThu thuThu) {
        //viết dữ liệu vào database
        ContentValues values = new ContentValues();
        values.put("maTT", thuThu.maTT);
        values.put("hoTen", thuThu.hoTen);
        values.put("matKhau", thuThu.matKhau);
        long row = database.insert("ThuThu", null, values);
        return (row > 0);
    }

    public boolean update(thuThu thuThu) {
        ContentValues values = new ContentValues();
//        values.put("maTT", thuThu.maTT);
        values.put("hoTen", thuThu.hoTen);
        values.put("matKhau", thuThu.matKhau);
        long row = database.update("ThuThu", values, "maTT=?", new String[]{thuThu.maTT});
        return (row > 0);
    }

    public boolean delete(String maTT) {
        long row = database.delete("ThuThu", "maTT=?", new String[]{maTT});
        return (row > 0);
    }
}
