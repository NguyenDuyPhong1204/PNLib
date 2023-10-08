package com.example.pnlib.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pnlib.DAO.thongKeDAO;
import com.example.pnlib.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class flDoanhThu extends Fragment {

    Button btnTuNgay, btnDenNgay, btnDoanhThu;
    EditText edTuNgay, edDenNgay;
    TextView tvDoanhThu;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    int getYear, getMonth, getDay;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fl_doanh_thu, null);

        edDenNgay = view.findViewById(R.id.edDenNgay);
        edTuNgay = view.findViewById(R.id.edTuNgay);
        tvDoanhThu = view.findViewById(R.id.tvDoanhThu);
        btnTuNgay = view.findViewById(R.id.btnTuNgay);
        btnDenNgay = view.findViewById(R.id.btnDenNgay);
        btnDoanhThu = view.findViewById(R.id.btnDoanhThu);

        DatePickerDialog.OnDateSetListener dateTuNgay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                getYear = year;
                getMonth = month;
                getDay = dayOfMonth;
                GregorianCalendar gregorianCalendar = new GregorianCalendar(getYear,getMonth,getDay);
                edTuNgay.setText(simpleDateFormat.format(gregorianCalendar.getTime()));
            }
        };
        DatePickerDialog.OnDateSetListener dateDenNgay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                getYear = year;
                getMonth = month;
                getDay = dayOfMonth;
                GregorianCalendar gregorianCalendar = new GregorianCalendar(getYear,getMonth,getDay);
                edDenNgay.setText(simpleDateFormat.format(gregorianCalendar.getTime()));
            }
        };

        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                getYear = calendar.get(Calendar.YEAR);
                getMonth = calendar.get(Calendar.MONTH);
                getDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getActivity(),0,dateTuNgay,getYear,getMonth,getDay);
                dialog.show();
            }
        });
        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                getYear = calendar.get(Calendar.YEAR);
                getMonth = calendar.get(Calendar.MONTH);
                getDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getActivity(),0,dateDenNgay,getYear,getMonth,getDay);
                dialog.show();
            }
        });
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay = edTuNgay.getText().toString();
                String denNgay = edDenNgay.getText().toString();
                thongKeDAO thongKeDAO = new thongKeDAO(getActivity());
                tvDoanhThu.setText(thongKeDAO.doanhThu(tuNgay,denNgay) + "VNĐ");
            }
        });
        return view;
    }
}