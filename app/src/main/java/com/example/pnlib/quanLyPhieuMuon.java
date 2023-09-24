package com.example.pnlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.pnlib.Fragment.flDoanhThu;
import com.example.pnlib.Fragment.flDoiMatKhau;
import com.example.pnlib.Fragment.flLoaiSach;
import com.example.pnlib.Fragment.flPhieuMuon;
import com.example.pnlib.Fragment.flSach;
import com.example.pnlib.Fragment.flThanhVien;
import com.example.pnlib.Fragment.flTopSach;
import com.google.android.material.navigation.NavigationView;

public class quanLyPhieuMuon extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
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
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,  R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flPhieuMuon()).commit();
            navigationView.setCheckedItem(R.id.nav_PhieuMuon);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.nav_PhieuMuon){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flPhieuMuon()).commit();
        }else if(item.getItemId() == R.id.nav_LoaiSach){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flLoaiSach()).commit();
        }else if(item.getItemId() == R.id.nav_Sach){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flSach()).commit();
        }else if(item.getItemId() == R.id.nav_ThanhVien){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flThanhVien()).commit();
        }else if(item.getItemId() == R.id.nav_TopMuon){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flTopSach()).commit();
        }else if(item.getItemId() == R.id.nav_DoanhThu){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flDoanhThu()).commit();
        }else if(item.getItemId() == R.id.nav_DoiMatKhau){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new flDoiMatKhau()).commit();
        }else if(item.getItemId() == R.id.nav_DangXuat){
            startActivity(new Intent(quanLyPhieuMuon.this, dang_nhap.class));
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}