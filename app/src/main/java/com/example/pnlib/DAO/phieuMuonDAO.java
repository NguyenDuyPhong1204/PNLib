package com.example.pnlib.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Database.Dbhelper;
import com.example.pnlib.Entity.loaiSach;
import com.example.pnlib.Entity.phieuMuon;
import com.example.pnlib.Entity.topMuon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class phieuMuonDAO {
    private SQLiteDatabase database;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public phieuMuonDAO(Context context) {
        Dbhelper dbhelper = new Dbhelper(context);
        database = dbhelper.getWritableDatabase();
    }



    public ArrayList<phieuMuon> getAll() {
        String sql = "select * from PhieuMuon";
        return getData(sql);
    }

    //get theo id
    public phieuMuon getID(String id){
        String sql = "select * from PhieuMuon where maPhieu = ?";
        ArrayList<phieuMuon> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    public ArrayList<phieuMuon> getData(String sql, String...selectionArgs) {
        ArrayList<phieuMuon> list = new ArrayList<>();
//        String sql = "select PM.*, ThanhVien.hoTen, Sach.tenSach, ThuThu.hoTen from PhieuMuon " +
//                "inner join ThanhVien on PhieuMuon.maTV = ThanhVien.maTV " +
//                "inner join Sach on PhieuMuon.maSach = Sach.maSach " +
//                "inner join ThuThu on PhieuMuon.maTT = ThuThu.maTT";

            Cursor cursor = database.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                phieuMuon phieuMuon = new phieuMuon();
                phieuMuon.maPM = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maPhieu")));
                phieuMuon.maTV = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTV")));
                phieuMuon.maTT = cursor.getString(cursor.getColumnIndex("maTT"));
                phieuMuon.maSach =Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSach"))) ;
                try {
                    phieuMuon.ngay = dateFormat.parse(cursor.getString(cursor.getColumnIndex("ngayMuon")));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                phieuMuon.traSach = Integer.parseInt(cursor.getString(cursor.getColumnIndex("traSach")));
                phieuMuon.tienThue = Integer.parseInt(cursor.getString(cursor.getColumnIndex("tienThue")));
                list.add(phieuMuon);
            }

        return list;
    }

    public boolean insert(phieuMuon phieuMuon) {
        //viết dữ liệu vào database
        ContentValues values = new ContentValues();
        values.put("maTV", phieuMuon.maTV);
        values.put("maTT", phieuMuon.maTT);
        values.put("maSach", phieuMuon.maSach);
        values.put("ngayMuon", phieuMuon.ngay.getTime());
        values.put("traSach", phieuMuon.traSach);
        values.put("tienThue", phieuMuon.tienThue);
        long row = database.insert("PhieuMuon", null, values);
        return (row > 0);
    }

    public boolean update(phieuMuon phieuMuon) {
        ContentValues values = new ContentValues();
//        values.put("maPhieu", phieuMuon.maPM);
        values.put("maTV", phieuMuon.maTV);
        values.put("maTT", phieuMuon.maTT);
        values.put("maSach", phieuMuon.maSach);
        values.put("ngayMuon", phieuMuon.ngay.getTime());
        values.put("traSach", phieuMuon.traSach);
        values.put("tienThue", phieuMuon.tienThue);
        long row = database.update("PhieuMuon",values , "maPhieu = ?", new String[]{String.valueOf(phieuMuon.maPM)});
        return (row > 0);
    }

    public boolean delete(String id) {
        long row = database.delete("PhieuMuon", "maPhieu=?", new String[]{id});
        return (row > 0);
    }
}
