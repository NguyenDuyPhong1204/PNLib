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
        checkRemember();
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
                        if (thuThuDAO.checkDangNhap(edTen, matKhau)) {
                            remember(edTen,matKhau,true);
                            Intent intent = new Intent(dang_nhap.this,quanLyPhieuMuon.class);
                            intent.putExtra("TENDN",edTen);
                            startActivity(intent);
                            Toast.makeText(dang_nhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            txtTaiKhoan.setError("Sai tài khoản hoặc mật khẩu");
                            txtMatKhau.setError("Sai tài khoản hoặc mật khẩu");
                        }
                }
            }
        });


        btnThoatDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edTenDN.setText("");
                edMatKhau.setText("");
            }
        });
    }

    public void remember(String tenDN, String matKhau,boolean rem ){
        SharedPreferences s = getSharedPreferences("Acc.txt",MODE_PRIVATE);
        SharedPreferences.Editor e =s.edit();//tạo một đối tượng Edit để chỉnh sửa
        e.putString("TenDN",tenDN);//đặt Tên đăng nhập với khoá TenDN
        e.putString("MatKhau",matKhau);
        e.putBoolean("Rem",rem);
        e.apply();//áp dụng thay đổi
    }
    public void checkRemember(){
        SharedPreferences s = getSharedPreferences("Acc.txt",MODE_PRIVATE);
        String tenDN = s.getString("TenDN","");//lấy giá trị từ SharedPreferences
        String matKhau = s.getString("MatKhau","");
        boolean check = s.getBoolean("Rem",false);//lấy giá trị trạng thái của check box
        chkLuuTK.setChecked(check);
        if(chkLuuTK.isChecked()){
            edTenDN.setText(tenDN);
            edMatKhau.setText(matKhau);
        }
    }
}