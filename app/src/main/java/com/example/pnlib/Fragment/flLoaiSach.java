package com.example.pnlib.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pnlib.Adapter.adapterLoaiSach;
import com.example.pnlib.Adapter.adapterThanhVien;
import com.example.pnlib.DAO.loaiSachDAO;
import com.example.pnlib.DAO.thanhVienDAO;
import com.example.pnlib.Entity.loaiSach;
import com.example.pnlib.Entity.thanhVien;
import com.example.pnlib.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class flLoaiSach extends Fragment {
    RecyclerView rcvLS;
    FloatingActionButton fltAdd;
    ArrayList<loaiSach> list;
    loaiSachDAO lsDAO;
    adapterLoaiSach adapterLS;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fl_loai_sach,container,false);
        rcvLS = view.findViewById(R.id.rcvLoaiSach);
        fltAdd = view.findViewById(R.id.flAddLS);
        rcvLS.setLayoutManager(new LinearLayoutManager(getActivity()));

        lsDAO = new loaiSachDAO(getActivity());
        list = new ArrayList<>();
        list = lsDAO.getAll();
        adapterLS = new adapterLoaiSach(getActivity(),list);
        rcvLS.setAdapter(adapterLS);
        return view;
    }
}