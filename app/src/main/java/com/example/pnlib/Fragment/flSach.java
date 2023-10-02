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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pnlib.Adapter.adapterSach;
import com.example.pnlib.Adapter.spiner_sach;
import com.example.pnlib.DAO.loaiSachDAO;
import com.example.pnlib.DAO.sachDAO;
import com.example.pnlib.Entity.loaiSach;
import com.example.pnlib.Entity.sach;
import com.example.pnlib.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class flSach extends Fragment {
    RecyclerView rcvS;
    FloatingActionButton fltAdd;
    ArrayList<sach> list;
    ArrayList<loaiSach> listTen;
    sachDAO sachDAO;
    loaiSachDAO lsDAO;
    adapterSach adapterSach;
    spiner_sach spinerSach;
    int matl;

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
        adapterSach = new adapterSach(getActivity(), list);
        rcvS.setAdapter(adapterSach);

        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        return view;
    }

    private void add() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_sach, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        //

        EditText edTenS = view.findViewById(R.id.edTenSachA);
        EditText edGia = view.findViewById(R.id.edGiaThue);
        Spinner spTenLoai = view.findViewById(R.id.spLoaiSach);
        Button btnAdd = view.findViewById(R.id.btnAddS);
        TextInputLayout checkTen = view.findViewById(R.id.checkTenS);
        TextInputLayout checkGia = view.findViewById(R.id.checkTien);


        lsDAO = new loaiSachDAO(getActivity());
        listTen = new ArrayList<>();
        listTen = lsDAO.getAll();
        spinerSach = new spiner_sach(getActivity(), listTen);
        spTenLoai.setAdapter(spinerSach);
        spTenLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                matl = listTen.get(position).getMaLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenSach = edTenS.getText().toString();
                String gia = edGia.getText().toString();

                if (tenSach.isEmpty() || gia.isEmpty()) {
                    if (tenSach.equals("")) {
                        checkTen.setError("Không được để trống");
                    }else {
                        checkTen.setError(null);
                    }
                    if(gia.equals("")){
                        checkGia.setError("Không được để trống");
                    }else {
                        checkGia.setError(null);
                    }
                } else {
                  try {
                      int tien = Integer.parseInt(gia);
                      if(tien <= 0){
                          checkGia.setError("Tiền phải lớn hơn 0");
                          return;
                      }
                  }catch (NumberFormatException e){
                      checkGia.setError("Giá tiền phải là số");
                      return;
                  }
                    int ma = matl;
                    int giaTien = Integer.valueOf(gia);
                    sach sachA = new sach(tenSach, giaTien, ma);
                    if (sachDAO.insert(sachA) >0) {
                        list.clear();
                        list.addAll(sachDAO.getAll());
                        adapterSach.notifyDataSetChanged();
                        dialog.dismiss();
                        Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            dialog.dismiss();
            }
        });
    }
}