package com.example.tracnghiem.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tracnghiem.Adapter.Adapter_danhsach;
import com.example.tracnghiem.Controler.Btn_click;
import com.example.tracnghiem.Model.model_DanhSach_ngChoi;
import com.example.tracnghiem.Model.user;
import com.example.tracnghiem.R;
import com.example.tracnghiem.Utils.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ThongKe extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        Btn_click.turn_off_SSL();
        ImageButton trangchu = findViewById(R.id.trangchu);
        trangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongKe.this,ChonMon.class);
                intent.putExtra("obj", ChonMucCauHoi.player);
                startActivity(intent);

            }
        });
        RecyclerView view = findViewById(R.id.recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ThongKe.this,RecyclerView.VERTICAL,false);
        view.setLayoutManager(linearLayoutManager);
        setData(view);
    }
    private void setData(RecyclerView view){
        RequestQueue queue = Volley.newRequestQueue(ThongKe.this);
        String url = URL.path +"DanhSachThongKe.php";
        StringRequest request = new StringRequest(
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        List<model_DanhSach_ngChoi> listds = new ArrayList<>();
                        try{
                            JSONArray json = new JSONArray(response);
                            for(int i = 0 ; i < json.length() ; i ++){
                                JSONObject obj = json.getJSONObject(i);
                                byte[] byteArray = Base64.decode(obj.getString("anh"), Base64.DEFAULT);
                                listds.add(new model_DanhSach_ngChoi(obj.getString("ten"),obj.getString("diem"),byteArray));
                            }
                            Adapter_danhsach adapter = new Adapter_danhsach(listds);
                            view.setAdapter(adapter);
                        }catch(Exception ex){

                            Log.d(null,ex.toString());
                            Toast.makeText(ThongKe.this, ex.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d(null,error.toString());
                        Toast.makeText(ThongKe.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        queue.add(request);
    }
}