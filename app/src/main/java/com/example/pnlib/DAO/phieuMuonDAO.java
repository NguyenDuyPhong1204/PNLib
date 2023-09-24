package com.example.pnlib.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.pnlib.Database.Dbhelper;
import com.example.pnlib.Entity.loaiSach;
import com.example.pnlib.Entity.phieuMuon;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
    public phieuMuon getID(String id) {
        String sql = "select * from PhieuMuon where maPhieu = ?";
        ArrayList<phieuMuon> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    public ArrayList<phieuMuon> getData(String sql, String... selectionArgs) {
        ArrayList<phieuMuon> list = new ArrayList<>();
        String sqlPM = "SELECT PhieuMuon.*, ThanhVien.hoTen, ThuThu.hoTen, Sach.tenSach \n" +
                "from PhieuMuon INNER JOIN ThanhVien ON PhieuMuon.maTV = ThanhVien.maTV\n" +
                "                INNER JOIN ThuThu ON PhieuMuon.maTT = ThuThu.maTT\n" +
                "                 INNER JOIN Sach on PhieuMuon.maSach = Sach.maSach";
      try{
          Cursor cursor = database.rawQuery(sqlPM, null);
          if(cursor.getCount() > 0){
              cursor.moveToFirst();
              while (!cursor.isAfterLast()){
                  phieuMuon phieuMuon = new phieuMuon();
                  phieuMuon.setMaPM(cursor.getInt(0));

                  phieuMuon.setMaTT(cursor.getString(1));

                  phieuMuon.setMaTV(cursor.getInt(2));

                  phieuMuon.setMaSach(cursor.getInt(3));

                  phieuMuon.setTienThue(cursor.getInt(4));

                  phieuMuon.setTraSach(cursor.getInt(5));

                  phieuMuon.setTenSach(cursor.getString(6));

                  phieuMuon.setTenTT(cursor.getString(7));

                  phieuMuon.setTenTV(cursor.getString(8));

                  phieuMuon.setNgay(cursor.getString(9));
                  list.add(phieuMuon);
                  cursor.moveToNext();
              }
          }
      }catch (Exception e){
      }
//        while (cursor.moveToNext()) {
//           try {
//               phieuMuon phieuMuon = new phieuMuon();
//               phieuMuon.maPM = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maPhieu")));
//               phieuMuon.maTV = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTV")));
//               phieuMuon.maTT = cursor.getString(cursor.getColumnIndex("maTT"));
//               phieuMuon.maSach = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSach")));
//               phieuMuon.ngay = cursor.getString(cursor.getColumnIndex("ngayMuon"));
//               phieuMuon.tienThue = Integer.parseInt(cursor.getString(cursor.getColumnIndex("tienThue")));
//               phieuMuon.traSach = Integer.parseInt(cursor.getString(cursor.getColumnIndex("traSach")));
//               phieuMuon.tenTV = cursor.getString(cursor.getColumnIndex("tenThanhVien"));
//               phieuMuon.tenTT = cursor.getString(cursor.getColumnIndex("tenThuThu"));
//               phieuMuon.tenSach = cursor.getString(cursor.getColumnIndex("tenS"));
//               list.add(phieuMuon);
//           }catch (Exception e){
//
//           }
//        }

        return list;
    }

    public boolean insert(phieuMuon phieuMuon) {
        //viết dữ liệu vào database
        ContentValues values = new ContentValues();
        values.put("maTV", phieuMuon.maTV);
        values.put("maTT", phieuMuon.maTT);
        values.put("maSach", phieuMuon.maSach);
        values.put("ngayMuon", phieuMuon.ngay);
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
        values.put("ngayMuon", phieuMuon.ngay);
        values.put("traSach", phieuMuon.traSach);
        values.put("tienThue", phieuMuon.tienThue);
        long row = database.update("PhieuMuon", values, "maPhieu = ?", new String[]{String.valueOf(phieuMuon.maPM)});
        return (row > 0);
    }

    public boolean delete(String id) {
        long row = database.delete("PhieuMuon", "maPhieu=?", new String[]{id});
        return (row > 0);
    }
}
