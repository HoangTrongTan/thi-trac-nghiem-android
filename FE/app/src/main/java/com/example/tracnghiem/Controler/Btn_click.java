package com.example.tracnghiem.Controler;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;import androidx.annotation.Nullable;import com.android.volley.AuthFailureError;import com.android.volley.Request;import com.android.volley.RequestQueue;import com.android.volley.Response;import com.android.volley.VolleyError;import com.android.volley.toolbox.StringRequest;import com.android.volley.toolbox.Volley;
import com.example.tracnghiem.Activitys.ChonMon;
import com.example.tracnghiem.Activitys.ChonMucCauHoi;
import com.example.tracnghiem.Model.user;
import com.example.tracnghiem.Utils.URL;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Btn_click {
    public static void DangKi(Dialog dialog, Context context, String ten, String tk, String mk, byte[] anh){
        RequestQueue queue = Volley.newRequestQueue(context);
        String path = URL.path+"dangky.php";
        StringRequest request = new StringRequest(Request.Method.POST,path,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response != "0"){
                            dialog.dismiss();
                        }else{
                            Toast.makeText(context, "Có lỗi xảy ra !", Toast.LENGTH_SHORT).show();
                        }
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
                data.put("ten",ten);
                data.put("tk",tk);
                data.put("mk",mk);
                data.put("anh", Base64.encodeToString(anh,Base64.DEFAULT));
                return data;
            }
        };
        queue.add(request);
    }
    public static void DangNhap(Context context,String tk, String mk){
        RequestQueue queue = Volley.newRequestQueue(context);
        String path = URL.path+"dangnhap.php";
        StringRequest request = new StringRequest(Request.Method.POST,path,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("0")){
                    Toast.makeText(context, "Có lỗi xảy ra !", Toast.LENGTH_SHORT).show();
                }else{
                    try{
                        JSONObject obj = new JSONObject(response);
                        Intent intent = new Intent(context, ChonMon.class);
                        List<String> anh = new ArrayList<>();
                        anh.add(obj.getString("anh"));
                        user player = new user(obj.getString("ten"),anh ,Integer.parseInt(obj.getString("diem")), Integer.parseInt(obj.getString("id_tk")));
                        player.setId(Integer.parseInt(obj.getString("id_tk")));
                        intent.putExtra("obj",player);
                        context.startActivity(intent);
                    }catch(Exception ex){
                        Log.d(null,ex.toString());
                    }
                }
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
            data.put("tk",tk);
            data.put("mk",mk);
            return data;
        }
        };
        queue.add(request);
    }
    //Tắt chứng chỉ SSl
    public static void turn_off_SSL(){
        try {
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
