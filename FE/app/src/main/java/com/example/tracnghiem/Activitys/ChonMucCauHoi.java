package com.example.tracnghiem.Activitys;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tracnghiem.Controler.Btn_click;
import com.example.tracnghiem.Model.user;
import com.example.tracnghiem.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChonMucCauHoi extends AppCompatActivity {

    public static user player;
    private Button de,trungbinh,kho,trangchu;
    private ImageButton xemthuhang;
    private TextView ten_user,diem_user;
    private CircleImageView anhdaidien;
    private String loai;

    @SuppressLint("WrongViewCast")
    private void anhXa(){
        CauHoi.list_ch.clear();
        loai = getIntent().getStringExtra("loai");
        de = findViewById(R.id.de);
        trungbinh = findViewById(R.id.trungbinh);
        kho = findViewById(R.id.kho);
        trangchu = findViewById(R.id.trangchu);

        ten_user = findViewById(R.id.ten_user);
        diem_user = findViewById(R.id.diem_user);
        anhdaidien = findViewById(R.id.anhdaidien);
        xemthuhang = findViewById(R.id.xemthuhang);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("obj")) {
            player = (user)getIntent().getSerializableExtra("obj");
        } else {
            Toast.makeText(this, "Intent bị null hoặc không chuyển đc", Toast.LENGTH_LONG).show();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_muc_cau_hoi);
        Btn_click.turn_off_SSL();
        anhXa();

        byte[] byteArray = Base64.decode(player.getAnh().get(0), Base64.DEFAULT);

        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        anhdaidien.setImageBitmap(bitmap);
        diem_user.setText(String.valueOf(player.getDiem())+" đ");
        xemthuhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChonMucCauHoi.this,ThongKe.class));
            }
        });

        de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CauHoi.list_ch.clear();
                Intent intent = new Intent(ChonMucCauHoi.this,CauHoi.class);
                intent.putExtra("Diem","30");
                intent.putExtra("MucCauHoi","0");
                intent.putExtra("obj",player);
                intent.putExtra("loai",getIntent().getStringExtra("loai"));
                startActivity(intent);
            }
        });
        trungbinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CauHoi.list_ch.clear();
                Intent intent = new Intent(ChonMucCauHoi.this,CauHoi.class);
                intent.putExtra("Diem","60");
                intent.putExtra("MucCauHoi","1");
                intent.putExtra("obj",player);
                intent.putExtra("loai",getIntent().getStringExtra("loai"));
                startActivity(intent);
            }
        });
        kho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CauHoi.list_ch.clear();
                Intent intent = new Intent(ChonMucCauHoi.this,CauHoi.class);
                intent.putExtra("Diem","90");
                intent.putExtra("MucCauHoi","2");
                intent.putExtra("obj",player);
                intent.putExtra("loai",getIntent().getStringExtra("loai"));
                startActivity(intent);
            }
        });
    }

}