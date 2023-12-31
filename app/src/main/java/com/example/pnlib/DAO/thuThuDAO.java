package com.example.pnlib.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Database.Dbhelper;
import com.example.pnlib.Entity.sach;
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
    public thuThu getID(String id) {
        String sql = "select * from ThuThu where maTT = ?";
        ArrayList<thuThu> list = getData(sql, id);
        return list.get(0);
    }


    @SuppressLint("Range")
    public ArrayList<thuThu> getData(String sql, String...selectionArgs) {
        ArrayList<thuThu> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            thuThu thuThu = new thuThu();
            thuThu.setMaTT(cursor.getString(cursor.getColumnIndex("maTT")));
            thuThu.setHoTen(cursor.getString(cursor.getColumnIndex("hoTenTT")));
            thuThu.setMatKhau(cursor.getString(cursor.getColumnIndex("matKhau")));
            list.add(thuThu);
        }
        return list;
    }

    public long insert(thuThu thuThu) {
        //viết dữ liệu vào database
//        SQLiteDatabase db = get
        ContentValues values = new ContentValues();
        values.put("maTT", thuThu.getMaTT());
        values.put("hoTenTT", thuThu.getHoTen());
        values.put("matKhau", thuThu.getMatKhau());
        return database.insert("ThuThu", null, values);
    }

    public long update(thuThu thuThu) {
        ContentValues values = new ContentValues();
//        values.put("maTT", thuThu.maTT);
        values.put("hoTenTT", thuThu.getHoTen());
        values.put("matKhau", thuThu.getMatKhau());
        return database.update("ThuThu", values, "maTT=?", new String[]{thuThu.getMaTT()});
    }

    public long updatePass(thuThu thuThu) {
        ContentValues values = new ContentValues();
//        values.put("maTT", thuThu.maTT);
//        values.put("hoTenTT", thuThu.getHoTen());
        values.put("matKhau", thuThu.getMatKhau());
        return database.update("ThuThu", values, "maTT=?", new String[]{thuThu.getMaTT()});
    }

    public long delete(String maTT) {
        return database.delete("ThuThu", "maTT=?", new String[]{maTT});
    }
}
