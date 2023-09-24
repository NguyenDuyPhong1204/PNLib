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
import com.example.pnlib.DAO.sachDAO;
import com.example.pnlib.DAO.thanhVienDAO;
import com.example.pnlib.DAO.thuThuDAO;
import com.example.pnlib.Entity.phieuMuon;
import com.example.pnlib.Entity.thanhVien;
import com.example.pnlib.R;

import java.util.ArrayList;

public class adapterPhieuMuon extends RecyclerView.Adapter<adapterPhieuMuon.viewHolep> {
    Context context;
    ArrayList<phieuMuon> list;

    public adapterPhieuMuon(Context context, ArrayList<phieuMuon> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolep onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phieu_muon, null);
        return new viewHolep(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolep holder, int position) {
        holder.tvMaPhieu.setText(String.valueOf(list.get(position).getMaPM()));
        holder.tvNguoiM.setText(list.get(position).getTenTV());
        holder.tvTenSach.setText(list.get(position).getTenSach());
        holder.tvTienThue.setText(String.valueOf(list.get(position).getTienThue()));
        holder.tvTenTT.setText(list.get(position).getTenTT());
        String trangThai = "";
        if (list.get(position).traSach == 1) {
            trangThai = "Đã trả";
        } else {
            trangThai = "Chưa trả";
        }
        holder.tvTrangThai.setText(trangThai);
        holder.tvNgay.setText(list.get(position).getNgay());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolep extends RecyclerView.ViewHolder {
        TextView tvMaPhieu, tvNguoiM, tvTenSach, tvTienThue, tvTrangThai, tvNgay, tvTenTT;
        ImageView imgXoa;

        public viewHolep(@NonNull View itemView) {
            super(itemView);
            tvMaPhieu = itemView.findViewById(R.id.tvMaPhieu);
            tvNguoiM = itemView.findViewById(R.id.tvNguoiM);
            tvTenSach = itemView.findViewById(R.id.tvTenSachP);
            tvTienThue = itemView.findViewById(R.id.tvTienThue);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
            tvNgay = itemView.findViewById(R.id.tvNgayThue);
            tvTenTT = itemView.findViewById(R.id.tvTenTT);
            imgXoa = itemView.findViewById(R.id.imgDeletePM);

        }
    }
}
