package com.example.pnlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.pnlib.Fragment.flDoanhThu;
import com.example.pnlib.Fragment.flDoiMatKhau;
import com.example.pnlib.Fragment.flLoaiSach;
import com.example.pnlib.Fragment.flPhieuMuon;
import com.example.pnlib.Fragment.flSach;
import com.example.pnlib.Fragment.flTaoTtaiKhoan;
import com.example.pnlib.Fragment.flThanhVien;
import com.example.pnlib.Fragment.flTopSach;
import com.google.android.material.navigation.NavigationView;

public class quanLyPhieuMuon extends AppCompatActivity {
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_phieu_muon);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Thư viện Phương Nam");
        drawerLayout = findViewById(R.id.navigationDrawer);
        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,  R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //
        NavigationUser();
        //
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flPhieuMuon()).commit();
            navigationView.setCheckedItem(R.id.nav_PhieuMuon);
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("CommitTransaction")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.nav_PhieuMuon){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flPhieuMuon()).commit();
                    toolbar.setTitle("Quản lý phiếu mượn");
                }else if(item.getItemId() == R.id.nav_LoaiSach){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flLoaiSach()).commit();
                    toolbar.setTitle("Quản lý loại sách");
                }else if(item.getItemId() == R.id.nav_Sach){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flSach()).commit();
                    toolbar.setTitle("Quản lý sách");
                }else if(item.getItemId() == R.id.nav_ThanhVien){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flThanhVien()).commit();
                    toolbar.setTitle("Quản lý thành viên");
                }else if(item.getItemId() == R.id.nav_TopMuon){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flTopSach()).commit();
                    toolbar.setTitle("Top mượn sách");
                }else if(item.getItemId() == R.id.nav_DoanhThu){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flDoanhThu()).commit();
                    toolbar.setTitle("Doanh thu");
                }else if(item.getItemId() == R.id.nav_DoiMatKhau){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flDoiMatKhau()).commit();
                    toolbar.setTitle("Đổi mật khẩu");
                }else if(item.getItemId() == R.id.nav_DangXuat){
                    startActivity(new Intent(quanLyPhieuMuon.this, dang_nhap.class));
                }else if(item.getItemId() == R.id.nav_ThemThanhVien){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flTaoTtaiKhoan()).commit();
                    toolbar.setTitle("Thêm thủ thư");
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId() == R.id.nav_PhieuMuon){
//            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flPhieuMuon()).commit();
//        }else if(item.getItemId() == R.id.nav_LoaiSach){
//            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flLoaiSach()).commit();
//        }else if(item.getItemId() == R.id.nav_Sach){
//            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flSach()).commit();
//        }else if(item.getItemId() == R.id.nav_ThanhVien){
//            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flThanhVien()).commit();
//        }else if(item.getItemId() == R.id.nav_TopMuon){
//            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flTopSach()).commit();
//        }else if(item.getItemId() == R.id.nav_DoanhThu){
//            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flDoanhThu()).commit();
//        }else if(item.getItemId() == R.id.nav_DoiMatKhau){
//            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flDoiMatKhau()).commit();
//        }else if(item.getItemId() == R.id.nav_DangXuat){
//            startActivity(new Intent(quanLyPhieuMuon.this, dang_nhap.class));
//        }
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
    public void NavigationUser(){
        // Tìm TextView trong nav_header.xml
        NavigationView nv = findViewById(R.id.nav_view);
        View headerView = nv.getHeaderView(0);
        TextView tvUser = headerView.findViewById(R.id.tvNameNguoiDung);
        // Lấy tên người dùng từ Intent
        Intent i = getIntent();
        String username = i.getStringExtra("TENDN");
        // Cập nhật TextView với tên người dùng
        tvUser.setText("Welcome " + username);
        // điều hướng
        if (username.equals("admin")){
            nv.getMenu().findItem(R.id.nav_ThemThanhVien).setVisible(true);
        }
    }
}