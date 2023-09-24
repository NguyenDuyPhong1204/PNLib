package com.example.pnlib.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {
    public Dbhelper(Context context) {
        super(context, "PBLIB", null, 6);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // tạo bảng Thành Viên
        String createTableThanhVien = ("CREATE TABLE ThanhVien\n" +
                "(\n" +
                "    maTV INTEGER PRIMARY KEY AUTOINCREMENT ,\n" +
                "    hoTen  TEXT NOT NULL ,\n" +
                "    namSinh TEXT NOT NULL\n" +
                ")");
        database.execSQL(createTableThanhVien);
        database.execSQL("insert into ThanhVien values(0,'Nguyễn Duy Phong','2004')");

        // tạo bảng thủ thư
        String createTableThuThu = ("CREATE TABLE ThuThu" +
                "(maTT INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "hoTen TEXT NOT NULL, " +
                "matKhau TEXT NOT NULL)");
        database.execSQL(createTableThuThu);
//        database.execSQL("insert into ThuThu values ('TT01','Phong','phong1204')");

        //tạo bảng loại loại sách
        String createTableLoaiSach = ("CREATE TABLE LoaiSach" +
                "(maLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenLoai TEXT NOT NULL)");
        database.execSQL(createTableLoaiSach);
        database.execSQL("insert into LoaiSach values(0,'CNTT')");

        //tạo bảng sách
        String createTableSach = ("CREATE TABLE Sach" +
                "(maSach INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maLoai  INTEGER  REFERENCES LoaiSach(maLoai)," +
                "tenSach TEXT NOT NULL," +
                "giaThue TEXT NOT NULL)");
        database.execSQL(createTableSach);
        database.execSQL("insert into Sach values (0,0,'Android 2','20000')");

        //tạo bảng phiếu mượn
        String createTablePhieuMuon = ("CREATE TABLE PhieuMuon" +
                "(maPhieu INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maTV INTEGER REFERENCES ThanhVien(maTV)," +
                "maTT TEXT REFERENCES ThuThu(maTT)," +
                "maSach INTEGER REFERENCES Sach(maSach)," +
                "ngayMuon DATE NOT NULL," +
                "traSach INTEGER NOT NULL," +
                "tienThue INTEGER NOT NULL)");
        database.execSQL(createTablePhieuMuon);
//        database.execSQL("insert into PhieuMuon values(0,0,'TT01',0,'24/09/2023',1,20000)");


        //data mẫu

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        //bảng thành viên
        if(oldVersion != newVersion){
            database.execSQL("drop table if exists ThanhVien");
            //bảng thủ thư
            database.execSQL("drop table if exists ThuThu");
            //bảng loại sách
            database.execSQL("drop table if exists LoaiSach");
            //bảng sách
            database.execSQL("drop table if exists Sach");
            //bảng phiếu mượn
            database.execSQL("drop table if exists PhieuMuon");
            //gọi lại hàm onCreate
            onCreate(database);
        }
    }
}
