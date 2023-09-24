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
import com.example.pnlib.Fragment.flTaoTtaiKhoan;
import com.example.pnlib.Fragment.flThanhVien;
import com.example.pnlib.Fragment.flTopSach;
import com.google.android.material.navigation.NavigationView;

public class quan_ly_phieu_muon_Admin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_phieu_muon_admin);
        Toolbar toolbar = findViewById(R.id.toolbarA);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Thư viện Phương Nam");
        drawerLayout = findViewById(R.id.navigationDrawerA);
        NavigationView navigationView = findViewById(R.id.nav_viewA);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,  R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_contentA,new flPhieuMuon()).commit();
            navigationView.setCheckedItem(R.id.nav_PhieuMuonA);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.nav_PhieuMuonA){
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_contentA,new flPhieuMuon()).commit();
        }else if(item.getItemId() == R.id.nav_LoaiSachA){
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_contentA,new flLoaiSach()).commit();
        }else if(item.getItemId() == R.id.nav_SachA){
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_contentA,new flSach()).commit();
        }else if(item.getItemId() == R.id.nav_ThanhVienA){
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_contentA,new flThanhVien()).commit();
        }else if(item.getItemId() == R.id.nav_TopMuonA){
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_contentA,new flTopSach()).commit();
        }else if(item.getItemId() == R.id.nav_DoanhThuA){
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_contentA,new flDoanhThu()).commit();
        }else if(item.getItemId() == R.id.nav_DoiMatKhauA){
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_contentA,new flDoiMatKhau()).commit();
        }else if(item.getItemId() == R.id.nav_ThemThanhVien){
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_contentA,new flTaoTtaiKhoan()).commit();
        }
        else if(item.getItemId() == R.id.nav_DangXuatA){
            startActivity(new Intent(quan_ly_phieu_muon_Admin.this, dang_nhap.class));
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}