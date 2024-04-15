package com.example.tracnghiem.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tracnghiem.Model.user;
import com.example.tracnghiem.R;

public class ChonMon extends AppCompatActivity {
    private Button ctdlgt,ktpm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_mon);

        ctdlgt = findViewById(R.id.ctdlgt);
        ktpm = findViewById(R.id.ktpm);
        Button btndangxuat = findViewById(R.id.btndangxuat);
        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChonMon.this,Dangnhap2.class));
            }
        });

        ctdlgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChonMon.this,ChonMucCauHoi.class);
                intent.putExtra("loai","CTDL&GT");
                intent.putExtra("obj", (user)getIntent().getSerializableExtra("obj"));
                startActivity(intent);
            }
        });
        ktpm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChonMon.this,ChonMucCauHoi.class);
                intent.putExtra("loai","KTPM");
                intent.putExtra("obj",(user)getIntent().getSerializableExtra("obj"));
                startActivity(intent);
            }
        });
    }
}