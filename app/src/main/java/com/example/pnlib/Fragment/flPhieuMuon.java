package com.example.pnlib.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pnlib.Adapter.adapterPhieuMuon;
import com.example.pnlib.Adapter.adapterSach;
import com.example.pnlib.Adapter.spinerTT;
import com.example.pnlib.Adapter.spiner_TV;
import com.example.pnlib.Adapter.spiner_sach;
import com.example.pnlib.Adapter.sqpnerTS;
import com.example.pnlib.DAO.phieuMuonDAO;
import com.example.pnlib.DAO.sachDAO;
import com.example.pnlib.DAO.thanhVienDAO;
import com.example.pnlib.DAO.thuThuDAO;
import com.example.pnlib.Entity.phieuMuon;
import com.example.pnlib.Entity.sach;
import com.example.pnlib.Entity.thanhVien;
import com.example.pnlib.Entity.thuThu;
import com.example.pnlib.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class flPhieuMuon extends Fragment {
    RecyclerView rcvPM;
    FloatingActionButton fltAdd;
    ArrayList<phieuMuon> list;
    ArrayList<thanhVien> listTV;
    ArrayList<thuThu> listTT;
    ArrayList<sach> listS;
    phieuMuonDAO pmDAO;
    adapterPhieuMuon adapterPhieuMuon;
    thuThuDAO thuThuDAO;
    thanhVienDAO tvDAO;
    sachDAO sachDAO;
    spiner_TV spinerTv;
    spinerTT spinerTT;
    sqpnerTS spinerTS;
    String maTT;
    int maTV;
    int maSach,tienThue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fl_phieu_muon, container, false);
        rcvPM = view.findViewById(R.id.rcvPhieuMuon);
        fltAdd = view.findViewById(R.id.flAddPM);
        rcvPM.setLayoutManager(new LinearLayoutManager(getActivity()));

        pmDAO = new phieuMuonDAO(getActivity());
        list = new ArrayList<>();
        list = pmDAO.getAll();
        adapterPhieuMuon = new adapterPhieuMuon(getActivity(), list);
        rcvPM.setAdapter(adapterPhieuMuon);

        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
        return view;
    }
    private void add(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_pm,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        //ánh xạ
        TextView tvMaPM = view.findViewById(R.id.tvMaPM_them);
        TextView tvTien = view.findViewById(R.id.tvTienPM_them);
        TextView tvNgay = view.findViewById(R.id.tvNgayPM_them);
        CheckBox chkTT = view.findViewById(R.id.chkTrangThai_Pm_them);
        Spinner spTT = view.findViewById(R.id.sp_tenTT_PMA);
        Spinner spTV = view.findViewById(R.id.sp_TenTV_PmA);
        Spinner spTS = view.findViewById(R.id.sp_TenS_PMA);
        Button btnAdd = view.findViewById(R.id.btnAdd_PM);
        Button btnCancel = view.findViewById(R.id.btnCancel_Pm_them);

        //



        //
        thuThuDAO = new thuThuDAO(getActivity());
        listTT = new ArrayList<>();
        listTT = thuThuDAO.getAll();
        spinerTT = new spinerTT(getActivity(),listTT);
        spTT.setAdapter(spinerTT);
        spTT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTT = listTT.get(position).getMaTT();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tvDAO = new thanhVienDAO(getActivity());
        listTV = new ArrayList<>();
        listTV = tvDAO.getAll();
        spinerTv = new spiner_TV(getActivity(),listTV);
        spTV.setAdapter(spinerTv);
        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTV = listTV.get(position).getMaTV();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sachDAO = new sachDAO(getActivity());
        listS = new ArrayList<>();
        listS = sachDAO.getAll();
        spinerTS = new sqpnerTS(getActivity(),listS);
        spTS.setAdapter(spinerTS);
        spTS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listS.get(position).getMaSach();
                tienThue = listS.get(position).getGiaThue();
                tvTien.setText(String.valueOf(tienThue));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             phieuMuon phieuMuon = new phieuMuon();
             phieuMuon.setMaTV(maTV);
             phieuMuon.setMaTT(maTT);
             phieuMuon.setMaSach(maSach);
             phieuMuon.setNgay(new Date());
             phieuMuon.setTienThue(tienThue);
             if(chkTT.isChecked()){
                 phieuMuon.setTraSach(1);
             }else{
                 phieuMuon.setTraSach(0);
             }
             if(pmDAO.insert(phieuMuon)){
                 list.clear();
                 list.addAll(pmDAO.getAll());
                 adapterPhieuMuon.notifyDataSetChanged();
                 dialog.dismiss();
                 Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
             }else{
                 Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
             }
            }
        });

    }


}