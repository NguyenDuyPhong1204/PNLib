package com.example.pnlib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class dang_nhap extends AppCompatActivity {
    EditText edTenDN, edMatKhau;
    Button btnDangNhap, btnThoatDN;
    CheckBox chkLuuTK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edTenDN = findViewById(R.id.edTenDN);
        edMatKhau = findViewById(R.id.edMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnThoatDN = findViewById(R.id.btnHuyDN);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edTen = edTenDN.getText().toString();
                String matKhau = edMatKhau.getText().toString();
                if(edTen.equals("admin") && matKhau.equals("admin")){
                    Toast.makeText(dang_nhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(dang_nhap.this, quan_ly_phieu_muon_Admin.class));
                }
            }
        });
    }
}