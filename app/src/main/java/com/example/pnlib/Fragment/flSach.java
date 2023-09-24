package com.example.pnlib.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pnlib.Adapter.adapterLoaiSach;
import com.example.pnlib.Adapter.adapterSach;
import com.example.pnlib.DAO.loaiSachDAO;
import com.example.pnlib.DAO.sachDAO;
import com.example.pnlib.Entity.loaiSach;
import com.example.pnlib.Entity.sach;
import com.example.pnlib.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class flSach extends Fragment {
    RecyclerView rcvS;
    FloatingActionButton fltAdd;
    ArrayList<sach> list;
    sachDAO sachDAO;
    adapterSach adapterSach;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fl_sach, container, false);
        rcvS = view.findViewById(R.id.rcvSach);
        fltAdd = view.findViewById(R.id.fltAddS);
        rcvS.setLayoutManager(new LinearLayoutManager(getActivity()));

        sachDAO = new sachDAO(getActivity());
        list = new ArrayList<>();
        list = sachDAO.getAll();
        adapterSach = new adapterSach(getActivity(),list);
        rcvS.setAdapter(adapterSach);
        return view;
    }
}