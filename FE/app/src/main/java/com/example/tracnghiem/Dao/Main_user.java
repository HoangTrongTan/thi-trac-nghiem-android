package com.example.tracnghiem.Dao;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tracnghiem.Activitys.ChonMucCauHoi;
import com.example.tracnghiem.Utils.URL;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Main_user {
    public static void DangNhap(Context context, CircleImageView image, TextView ten_user,TextView diem_user){
        RequestQueue queue = Volley.newRequestQueue(context);
        String path = URL.path+"dangnhap.php";
        StringRequest request = new StringRequest(Request.Method.POST,path,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "======="+response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                Log.d("ok","$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+error);
            }
        }
        ){@Nullable
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> data = new HashMap<>();

            return data;
        }
        };
        queue.add(request);
    }
}
