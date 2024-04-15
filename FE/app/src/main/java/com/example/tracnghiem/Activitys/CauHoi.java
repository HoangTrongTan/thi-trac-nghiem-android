package com.example.tracnghiem.Activitys;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tracnghiem.Controler.Btn_click;
import com.example.tracnghiem.Controler.Dialog_CauHoi;
import com.example.tracnghiem.Model.model_cauhoi;
import com.example.tracnghiem.Model.user;
import com.example.tracnghiem.R;
import com.example.tracnghiem.Utils.Toast_type;
import com.example.tracnghiem.Utils.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CauHoi extends AppCompatActivity {
    static List<model_cauhoi> list_ch = new ArrayList<>();
    private long second = 0;
    private int muc_cau_hoi = 0;
    private int diem = 0;
    private CircleImageView anhdaidien;
    private TextView dem_so,cauhoi;
    private Button da_a,da_b,da_c,da_d;
    private  int dapan = 0;
    private int dem,traloi_dung;
    private CountDownTimer countdownTimer;
    public user player;
    private MediaPlayer nghe = null;
    private int diemtunngcau = 0;
    private ImageButton ngungtrochoi;
    private LocalTime Thoigianbatdau = LocalTime.now();
    private LocalTime ThoiGianKetthuc;
//
    private void Controll(){
        diem = Integer.parseInt(getIntent().getStringExtra("Diem"));
        diemtunngcau = diem/30;
        anhdaidien = findViewById(R.id.anhdaidien);
        dem_so = findViewById(R.id.dem_so);
        cauhoi = findViewById(R.id.cauhoi);
        da_a = findViewById(R.id.da_a);
        da_b = findViewById(R.id.da_b);
        da_c = findViewById(R.id.da_c);
        da_d = findViewById(R.id.da_d);
        ngungtrochoi = findViewById(R.id.ngungtrochoi);
        ngungtrochoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_CauHoi.NgungTroChoi(CauHoi.this,Gravity.CENTER);
            }
        });

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("obj")) {
            player = (user)getIntent().getSerializableExtra("obj");
        } else {
            Toast.makeText(this, "Intent bị null hoặc không chuyển đc", Toast.LENGTH_LONG).show();
        }
        byte[] byteArray = Base64.decode(player.getAnh().get(0), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        anhdaidien.setImageBitmap(bitmap);
        RequestQueue queue = Volley.newRequestQueue(CauHoi.this);
        String url = URL.path+"getAllCauhoi.php";

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.S)
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray js = new JSONArray(response);
                            for(int i = 0; i < js.length(); i ++) {
                                JSONObject obj = js.getJSONObject(i);
                                list_ch.add(new model_cauhoi(obj.getString("Ten_Cauhoi"), obj.getString("da_a"), obj.getString("da_b"), obj.getString("da_c"), obj.getString("da_d"), obj.getInt("kq")));
                            }
                            Toast.makeText(CauHoi.this, String.valueOf(list_ch.size()), Toast.LENGTH_SHORT).show();
                            random_question();
                            click();
                            tick();
                        }catch (Exception ex){
                            Toast.makeText(CauHoi.this, ex.toString(), Toast.LENGTH_LONG).show();
                            Log.d("",ex.toString());
                        }
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CauHoi.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("****************",error.toString());
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                data.put("Muc_CauHoi",getIntent().getStringExtra("MucCauHoi"));
                data.put("loai",getIntent().getStringExtra("loai"));
                return data;
            }
        };
        queue.add(request);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi);
        Controll();
        if(dem >= 30){
            ThoiGianKetthuc = LocalTime.now();
            Duration tong_time = Duration.between(Thoigianbatdau, ThoiGianKetthuc);
            Dialog_CauHoi.Show_dialog_CauHoi(CauHoi.this, Gravity.CENTER, String.valueOf((traloi_dung >= dem? diem:diemtunngcau*traloi_dung)),String.valueOf(traloi_dung),String.valueOf(tong_time.toHours() == 0?tong_time.toMillis():tong_time.toMinutes() )   );
            Dialog_CauHoi.UpdateDiem(CauHoi.this,String.valueOf(diemtunngcau*traloi_dung),String.valueOf(ChonMucCauHoi.player.getId()),String.valueOf(tong_time.toHours() == 0?tong_time.toMillis():tong_time.toMinutes() )   );
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.S)
    private void tick(){
        Toast.makeText(this, "Còn "+dem+" câu, đúng "+traloi_dung+" /30", Toast.LENGTH_SHORT).show();
        if(dem >= 30){
            countdownTimer.cancel();
            ThoiGianKetthuc = LocalTime.now();
            Duration tong_time = Duration.between(Thoigianbatdau, ThoiGianKetthuc);
            Dialog_CauHoi.Show_dialog_CauHoi(CauHoi.this, Gravity.CENTER, String.valueOf((traloi_dung >= dem? diem:diemtunngcau*traloi_dung)),String.valueOf(traloi_dung),String.valueOf(tong_time.toHours() == 0?tong_time.toMillis():tong_time.toMinutes() ) );

            Dialog_CauHoi.UpdateDiem(CauHoi.this,String.valueOf(diemtunngcau*traloi_dung),String.valueOf(ChonMucCauHoi.player.getId()),String.valueOf(tong_time.toHours() == 0?tong_time.toMillis():tong_time.toMinutes() )  );
            return;
        }

        countdownTimer =  new CountDownTimer(60000, 1000) { // đếm ngược 30 giây, với khoảng thời gian là 1 giây
            public void onTick(long millisUntilFinished) {
                dem_so.setText("00:"+millisUntilFinished / 1000);
            }
            public void onFinish() {
                dem++;
                random_question();
                tick();
            }
        }.start();
    }
    private void random_question(){
        if(dem >= 30){
            countdownTimer.cancel();
            ThoiGianKetthuc = LocalTime.now();
            Duration tong_time = Duration.between(Thoigianbatdau, ThoiGianKetthuc);
            Dialog_CauHoi.Show_dialog_CauHoi(CauHoi.this, Gravity.CENTER, String.valueOf((traloi_dung >= dem? diem:diemtunngcau*traloi_dung)),String.valueOf(traloi_dung),String.valueOf(tong_time.toHours() == 0?tong_time.toMillis():tong_time.toMinutes() )  );
            Dialog_CauHoi.UpdateDiem(CauHoi.this,String.valueOf(diemtunngcau*traloi_dung),String.valueOf(ChonMucCauHoi.player.getId()),String.valueOf(tong_time.toHours() == 0?tong_time.toMillis():tong_time.toMinutes()  )  );

            return;
        }
        Random random = new Random();
        int n = list_ch.size() ;
        int num = random.nextInt(n-1);
        dapan = list_ch.get(num).getKq();

        cauhoi.setText(list_ch.get(num).getCauhoi());
        da_a.setText(list_ch.get(num).getDa_a());
        da_b.setText(list_ch.get(num).getDa_b());
        da_c.setText(list_ch.get(num).getDa_c());
        da_d.setText(list_ch.get(num).getDa_d());
    }
    private void click(){
        Toast.makeText(this, "Còn "+dem+" câu, đúng "+traloi_dung+" /30"+" :"+diemtunngcau, Toast.LENGTH_SHORT).show();
        if(dem >= 30){
            countdownTimer.cancel();
            ThoiGianKetthuc = LocalTime.now();
            Duration tong_time = Duration.between(Thoigianbatdau, ThoiGianKetthuc);
            Dialog_CauHoi.UpdateDiem(CauHoi.this,String.valueOf(diemtunngcau*traloi_dung),String.valueOf(ChonMucCauHoi.player.getId()),String.valueOf(tong_time.toHours() == 0?tong_time.toMillis():tong_time.toMinutes() )  );

            return;
        }
        dem ++;
        da_a.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.S)
            @Override
            public void onClick(View v) {
                int temp = 1;
                if(dapan == temp){
                    nghe =MediaPlayer.create(CauHoi.this,R.raw.dung);nghe.start();
                    random_question();
                    countdownTimer.cancel();
                    traloi_dung ++;
                    tick();
//                    Toast_type.Toast_Style(CauHoi.this,R.drawable.okok,Toast.LENGTH_SHORT);
                    dem ++;

                }else{
                    nghe =MediaPlayer.create(CauHoi.this,R.raw.sai);nghe.start();
//                    Toast_type.Toast_Style(CauHoi.this,R.drawable.huhu,Toast.LENGTH_SHORT);
                    random_question();
                    countdownTimer.cancel();
                    tick();
                    dem ++;
                }
            }
        });
        da_b.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.S)
            @Override
            public void onClick(View v) {
                int temp = 2;
                if(dapan == temp){
                    nghe =MediaPlayer.create(CauHoi.this,R.raw.dung);nghe.start();

//                    Toast_type.Toast_Style(CauHoi.this,R.drawable.okok,Toast.LENGTH_SHORT);
                    random_question();
                    traloi_dung ++;
                    countdownTimer.cancel();
                    tick();
                    dem ++;
                }else{
                    nghe =MediaPlayer.create(CauHoi.this,R.raw.sai);nghe.start();
//                    Toast_type.Toast_Style(CauHoi.this,R.drawable.huhu,Toast.LENGTH_SHORT);
                    random_question();countdownTimer.cancel();
                    tick();
                    dem ++;
                }
            }
        });
        da_c.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.S)
            @Override
            public void onClick(View v) {
                int temp = 3;
                if(dapan == temp){
                    nghe =MediaPlayer.create(CauHoi.this,R.raw.dung);nghe.start();
//                    Toast_type.Toast_Style(CauHoi.this,R.drawable.okok,Toast.LENGTH_SHORT);
                    random_question();countdownTimer.cancel();
                    traloi_dung ++;
                    tick();
                    dem ++;
                }else{
                    nghe =MediaPlayer.create(CauHoi.this,R.raw.sai);nghe.start();
//                    Toast_type.Toast_Style(CauHoi.this,R.drawable.huhu,Toast.LENGTH_SHORT);
                    random_question();countdownTimer.cancel();
                    tick();
                    dem ++;
                }
            }
        });
        da_d.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.S)
            @Override
            public void onClick(View v) {
                int temp = 4;
                if(dapan == temp){
//                    Toast_type.Toast_Style(CauHoi.this,R.drawable.okok,Toast.LENGTH_SHORT);
                    nghe =MediaPlayer.create(CauHoi.this,R.raw.dung);nghe.start();
                    random_question();countdownTimer.cancel();
                    traloi_dung ++;
                    tick();
                    dem ++;
                }else{
                    nghe =MediaPlayer.create(CauHoi.this,R.raw.sai);nghe.start();
//                    Toast_type.Toast_Style(CauHoi.this,R.drawable.huhu,Toast.LENGTH_SHORT);
                    random_question();countdownTimer.cancel();
                    tick();
                    dem ++;
                }
            }
        });
    }
}