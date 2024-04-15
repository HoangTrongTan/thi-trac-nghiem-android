package com.example.tracnghiem.Controler;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tracnghiem.Activitys.CauHoi;
import com.example.tracnghiem.Activitys.ChonMon;
import com.example.tracnghiem.Activitys.ChonMucCauHoi;
import com.example.tracnghiem.Activitys.ThongKe;
import com.example.tracnghiem.Activitys.XemKetQua;
import com.example.tracnghiem.Model.model_cauhoi;
import com.example.tracnghiem.R;
import com.example.tracnghiem.Utils.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Dialog_CauHoi {
    public static void Show_dialog_CauHoi(Context context,int gravity,String diem,String socautraloidung,String tong_tg){
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout_hetgio);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams atribuites = window.getAttributes();
        atribuites.gravity = gravity;
        window.setAttributes(atribuites);

        Button tiep = dialog.findViewById(R.id.tiep);
        Button xemketqua = dialog.findViewById(R.id.xemketqua);

        tiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = ChonMucCauHoi.player.getDiem();
                ChonMucCauHoi.player.setDiem(n+Integer.parseInt(diem));
                Intent intent = new Intent(context, ChonMon.class);
                intent.putExtra("obj", ChonMucCauHoi.player);
                context.startActivity(intent);
            }
        });
        xemketqua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, XemKetQua.class);
                intent.putExtra("diem",diem);
                intent.putExtra("socautraloidung",socautraloidung);
                intent.putExtra("tongtg",tong_tg);
                context.startActivity(intent);
            }
        });

        dialog.setCancelable(false);
        dialog.show();
    }
    public static void UpdateDiem(Context context,String diem,String tk,String tong_tg){

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = URL.path+"capnhatdiem.php";

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("0")){
                            Toast.makeText(context, "Cập nhật không thành công"+tong_tg, Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(context, "Cập nhật thành công"+tong_tg, Toast.LENGTH_LONG).show();
                        }
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                Log.d(null,error.toString());
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                data.put("diem",diem);
                data.put("tk",tk);
                data.put("tong_tg",tong_tg);
                return data;
            }
        };
        queue.add(request);
    }
    public static void NgungTroChoi(Context context,int gravity){
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.ngungtrochoi);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams atribuites = window.getAttributes();
        atribuites.gravity = gravity;
        window.setAttributes(atribuites);

        Button tiep = dialog.findViewById(R.id.tiep);
        Button venha = dialog.findViewById(R.id.venha);

        tiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        venha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ChonMon.class);
                intent.putExtra("obj", ChonMucCauHoi.player);
                context.startActivity(intent);
            }
        });

        dialog.setCancelable(false);
        dialog.show();
    }
}
