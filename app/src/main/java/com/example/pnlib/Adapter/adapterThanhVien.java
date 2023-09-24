package com.example.pnlib.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.DAO.thanhVienDAO;
import com.example.pnlib.Entity.thanhVien;
import com.example.pnlib.R;

import java.util.ArrayList;

public class adapterThanhVien extends RecyclerView.Adapter<adapterThanhVien.viewHolep>{
Context context;
private final ArrayList<thanhVien> list;
thanhVienDAO tvDAO;

    public adapterThanhVien(Context context, ArrayList<thanhVien> list) {
        this.context = context;
        this.list = list;
        tvDAO = new thanhVienDAO(context);
    }

    @NonNull
    @Override
    public viewHolep onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qltv,null);
        return new viewHolep(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolep holder, int position) {
        holder.tvMaTV.setText(String.valueOf(list.get(position).maTV));
        holder.tvHoTen.setText(list.get(position).hoTen);
        holder.tvNamSinh.setText(list.get(position).namSinh);
        thanhVien thanhVien = list.get(position);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolep extends RecyclerView.ViewHolder{
    TextView tvMaTV, tvHoTen, tvNamSinh;

    ImageView imgXoa;
        public viewHolep(@NonNull View itemView) {
            super(itemView);
            tvMaTV = itemView.findViewById(R.id.tvMaTV);
            tvHoTen = itemView.findViewById(R.id.tvTenTV);
            tvNamSinh = itemView.findViewById(R.id.tvNamSinh);
            imgXoa = itemView.findViewById(R.id.imgDelete);
        }
    }
}
