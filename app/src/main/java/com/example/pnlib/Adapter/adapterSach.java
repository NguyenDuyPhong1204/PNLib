package com.example.pnlib.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.DAO.sachDAO;
import com.example.pnlib.Entity.sach;
import com.example.pnlib.R;

import java.util.ArrayList;

public class adapterSach extends RecyclerView.Adapter<adapterSach.viewHolep>{
    Context context;
    private final ArrayList<sach> list;
    sachDAO sachDAO;

    public adapterSach(Context context, ArrayList<sach> list) {
        this.context = context;
        this.list = list;
        sachDAO = new sachDAO(context);
    }

    @NonNull
    @Override
    public viewHolep onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qls,parent,false);
       return new viewHolep(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolep holder, int position) {
        holder.tvMaS.setText(String.valueOf(list.get(position).maSach));
        holder.tvTenSach.setText(list.get(position).tenSach);
        holder.tvGiaThue.setText(String.valueOf(list.get(position).giaThue));
        holder.tvmaLoai.setText(String.valueOf(list.get(position).maLoai));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolep extends RecyclerView.ViewHolder{
        TextView tvMaS, tvTenSach, tvGiaThue,tvmaLoai;
        ImageView imgXoa;
        public viewHolep(@NonNull View itemView) {
            super(itemView);
            tvMaS = itemView.findViewById(R.id.tvMaSach);
            tvTenSach = itemView.findViewById(R.id.tvTenSach);
            tvGiaThue = itemView.findViewById(R.id.tvGiaThue);
            tvmaLoai = itemView.findViewById(R.id.tvMaLoai);
            imgXoa = itemView.findViewById(R.id.imgDeleteS);
        }
    }
}
