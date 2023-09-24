
package com.example.pnlib.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pnlib.Adapter.adapterThanhVien;
import com.example.pnlib.DAO.thanhVienDAO;
import com.example.pnlib.Entity.thanhVien;
import com.example.pnlib.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class flThanhVien extends Fragment {
    RecyclerView rcvTV;
    FloatingActionButton fltAdd;
    ArrayList<thanhVien> list;
    thanhVienDAO tvDAO;
    adapterThanhVien adapterTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fl_thanh_vien,container,false);
        rcvTV = view.findViewById(R.id.rcvThanhVien);
        fltAdd = view.findViewById(R.id.fltAdd);
        rcvTV.setLayoutManager(new LinearLayoutManager(getActivity()));

        tvDAO = new thanhVienDAO(getActivity());
        list = new ArrayList<>();
        list = tvDAO.getAll();
        adapterTV = new adapterThanhVien(getActivity(),list);
        rcvTV.setAdapter(adapterTV);

       return view;
    }
}