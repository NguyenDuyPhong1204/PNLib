package com.example.pnlib;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pnlib.DAO.thuThuDAO;
import com.google.android.material.textfield.TextInputLayout;

public class dang_nhap extends AppCompatActivity {
    EditText edTenDN, edMatKhau;
    TextInputLayout txtTaiKhoan, txtMatKhau;
    Button btnDangNhap, btnThoatDN;
    CheckBox chkLuuTK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edTenDN = findViewById(R.id.edTenDN);
        edMatKhau = findViewById(R.id.edMatKhau);
        txtTaiKhoan = findViewById(R.id.txttenDN);
        txtMatKhau = findViewById(R.id.txtmatKhau);
        chkLuuTK = findViewById(R.id.chkLuuTK);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnThoatDN = findViewById(R.id.btnHuyDN);
        thuThuDAO thuThuDAO = new thuThuDAO(this);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edTen = edTenDN.getText().toString();
                String matKhau = edMatKhau.getText().toString();

                if (edTen.isEmpty() || matKhau.isEmpty()) {
                    if (edTen.equals("")) {
                        txtTaiKhoan.setError("Vui lòng nhập tài khoản");
                    }
                    if (matKhau.equals("")) {
                        txtMatKhau.setError("Vui lòng nhập mật khẩu");
                    }
                } else {
                    if (edTen.equals("admin") && matKhau.equals("admin")) {
                        Toast.makeText(dang_nhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(dang_nhap.this, quan_ly_phieu_muon_Admin.class));
                    }else {
                        if (thuThuDAO.checkDangNhap(edTen, matKhau)) {
                            //lưu
                            SharedPreferences sharedPreferences = getSharedPreferences("ThongTin", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("TenDangNhap", edTen);
                            editor.putString("MatKhau",matKhau);
                            editor.commit();
                            startActivity(new Intent(dang_nhap.this, quanLyPhieuMuon.class));
                        } else {
                            txtTaiKhoan.setError("Sai tài khoản hoặc mật khẩu");
                            txtMatKhau.setError("Sai tài khoản hoặc mật khẩu");
                        }
                    }
                }
            }
        });
//        SharedPreferences sharedPreferences = getSharedPreferences("ThongTin",MODE_PRIVATE);
//        edMatKhau.setText(sharedPreferences.getString("TenDangNhap",""));
//        edTenDN.setText(sharedPreferences.getString("MatKhau",""));
//        chkLuuTK.isChecked();


        btnThoatDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edTenDN.setText("");
                edMatKhau.setText("");
            }
        });
    }

}