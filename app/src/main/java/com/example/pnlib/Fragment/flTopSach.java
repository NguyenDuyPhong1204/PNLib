package com.example.pnlib.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pnlib.Adapter.adapterThongKe;
import com.example.pnlib.DAO.thongKeDAO;
import com.example.pnlib.Entity.topMuon;
import com.example.pnlib.R;

import java.util.ArrayList;


public class flTopSach extends Fragment {
    RecyclerView rcvTop;
    adapterThongKe adapterThongKe;
    ArrayList<topMuon> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fl_top_sach,null);
        rcvTop = view.findViewById(R.id.rcvThongKe);
        rcvTop.setLayoutManager(new LinearLayoutManager(getActivity()));
        thongKeDAO thongKeDAO = new thongKeDAO(getActivity());
        list = new ArrayList<>();
        list = thongKeDAO.getTop();
        adapterThongKe = new adapterThongKe(getActivity(),list);
        rcvTop.setAdapter(adapterThongKe);

        return view;
    }
}