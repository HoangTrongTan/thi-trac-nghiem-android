package com.example.tracnghiem.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tracnghiem.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class XemKetQua extends AppCompatActivity {
    CircleImageView anhdaidien;
    TextView diemtong,SoCauTraLoiDung,Tong_tg;
    Button Trangchu,XemHang;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_ket_qua);

        anhdaidien = findViewById(R.id.anhdaidienn);
        byte[] byteArray = Base64.decode(ChonMucCauHoi.player.getAnh().get(0), Base64.DEFAULT);

        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        anhdaidien.setImageBitmap(bitmap);

        diemtong  = findViewById(R.id.diemtong);
        SoCauTraLoiDung = findViewById(R.id.SoCauTraLoiDung);
        Trangchu = findViewById(R.id.Trangchu);
        XemHang = findViewById(R.id.XemHang);
        Tong_tg = findViewById(R.id.Tong_tg);

        diemtong.setText("Điểm của bạn: "+getIntent().getStringExtra("diem"));
        SoCauTraLoiDung.setText("Số câu trả lời đúng: "+getIntent().getStringExtra("socautraloidung")+ "/30");
        Tong_tg.setText("Thời gian: "+getIntent().getStringExtra("tongtg")+" millis");

        Trangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = ChonMucCauHoi.player.getDiem();
                ChonMucCauHoi.player.setDiem(Integer.parseInt(n+getIntent().getStringExtra("diem")));
                Intent intent = new Intent(XemKetQua.this, ChonMon.class);
                intent.putExtra("obj", ChonMucCauHoi.player);
                startActivity(intent);
            }
        });
        XemHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(XemKetQua.this,ThongKe.class));
            }
        });
    }
}