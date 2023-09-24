package com.example.pnlib.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.DAO.loaiSachDAO;
import com.example.pnlib.Entity.loaiSach;
import com.example.pnlib.R;

import java.util.ArrayList;

public class adapterLoaiSach extends RecyclerView.Adapter<adapterLoaiSach.viewHolep>{
    Context context;
    private final ArrayList<loaiSach> list;
    loaiSachDAO loaiSachDAO;

    public adapterLoaiSach(Context context, ArrayList<loaiSach> list) {
        this.context = context;
        this.list = list;
        loaiSachDAO = new loaiSachDAO(context);
    }

    @NonNull
    @Override
    public viewHolep onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loaisach,parent,false);
       return new viewHolep(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolep holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolep extends RecyclerView.ViewHolder {
        TextView tvMaLoai, tvTenLoai;
        ImageView imgXoa;
        public viewHolep(@NonNull View itemView) {
            super(itemView);
            tvMaLoai = itemView.findViewById(R.id.tvMaTheLoai);
            tvTenLoai = itemView.findViewById(R.id.tvTenTL);
            imgXoa = itemView.findViewById(R.id.imgDeleteLS);
        }
    }
}
