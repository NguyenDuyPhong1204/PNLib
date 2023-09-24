package com.example.pnlib.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.DAO.phieuMuonDAO;
import com.example.pnlib.Entity.phieuMuon;
import com.example.pnlib.R;

import java.util.ArrayList;

public class adapterPhieuMuon extends RecyclerView.Adapter<adapterPhieuMuon.viewHolep>{
    Context context;
    private final ArrayList<phieuMuon> list;
    phieuMuonDAO pmDAO;

    public adapterPhieuMuon(Context context, ArrayList<phieuMuon> list) {
        this.context = context;
        this.list = list;
        pmDAO = new phieuMuonDAO(context);
    }

    @NonNull
    @Override
    public viewHolep onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phieu_muon,parent,false);
      return new viewHolep(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolep holder, int position) {
        holder.tvMaPhieu.setText(String.valueOf(list.get(position).maPM));
        holder.tvNguoiM.setText(list.get(position).maTT);
        holder.tvTenSach.setText(String.valueOf(list.get(position).maSach));
        holder.tvTienThue.setText(list.get(position).tienThue);
        holder.tvTrangThai.setText(list.get(position).traSach);
        holder.tvNgay.setText((CharSequence) list.get(position).ngay);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolep extends RecyclerView.ViewHolder {
        TextView tvMaPhieu, tvNguoiM, tvTenSach, tvTienThue, tvTrangThai, tvNgay;
        ImageView imgXoa;

        public viewHolep(@NonNull View itemView) {
            super(itemView);
            tvMaPhieu = itemView.findViewById(R.id.tvMaPhieu);
            tvNguoiM = itemView.findViewById(R.id.tvNguoiM);
            tvTenSach = itemView.findViewById(R.id.tvTenSachP);
            tvTienThue = itemView.findViewById(R.id.tvTienThue);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
            tvNgay = itemView.findViewById(R.id.tvNgayThue);
            imgXoa = itemView.findViewById(R.id.imgDeletePM);
        }
    }
}
