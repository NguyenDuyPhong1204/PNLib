package com.example.pnlib.Entity;

import java.util.Date;

public class phieuMuon {
    public int maPM;
    public String maTT;
    public int maTV, maSach, tienThue, traSach;
    public Date ngay;

    public phieuMuon() {
    }

    public phieuMuon(int maPM, String maTT, int maTV, int maSach, int tienThue, int traSach, Date ngay) {
        this.maPM = maPM;
        this.maTT = maTT;
        this.maTV = maTV;
        this.maSach = maSach;
        this.tienThue = tienThue;
        this.traSach = traSach;
        this.ngay = ngay;
    }


}
