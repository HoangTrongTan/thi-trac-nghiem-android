
package com.example.tracnghiem.Activitys;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tracnghiem.Controler.Btn_click;
import com.example.tracnghiem.R;
import com.example.tracnghiem.Utils.ImageLoader;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dangnhap2 extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK = 1;
    private TextInputLayout txt_layout_account,txt_layout_pass;
    private TextInputEditText txt_account,txt_pass;
    private Button btn_vao,btn_dangki;
    private CircleImageView img;
    private ActivityResultLauncher<Intent> resultLauncher;
    public void anhXa(){
        btn_dangki = findViewById(R.id.btn_dangki);
        btn_vao = findViewById(R.id.btn_vao);

        txt_layout_account = findViewById(R.id.txt_layout_account);
        txt_layout_pass = findViewById(R.id.txt_layout_pass);
        txt_account = findViewById(R.id.txt_account);
        txt_pass = findViewById(R.id.txt_pass);

        btn_vao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Btn_click.DangNhap(Dangnhap2.this,txt_account.getText().toString(),txt_pass.getText().toString());
            }
        });
        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK && result.getData() != null){
                    Uri uri = ImageLoader.getImageformUri(Dangnhap2.this,result.getData());
                    ImageLoader.loadImage(Dangnhap2.this,uri,img);
                }
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap2);
        Btn_click.turn_off_SSL();
        anhXa();
        btn_dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog_DangKy(Gravity.CENTER);
            }
        });
    }
    public void openDialog_DangKy(int Gravity){
        Dialog dialog = new Dialog(Dangnhap2.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_dangki);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams atribuites = window.getAttributes();
        atribuites.gravity = Gravity;
        window.setAttributes(atribuites);

        CircleImageView anh_user = dialog.findViewById(R.id.anh_user);
        TextInputLayout txtlayout_name = dialog.findViewById(R.id.txtlayout_name);
        TextInputLayout txtlayout_taikhoan = dialog.findViewById(R.id.txtlayout_taikhoan);
        TextInputLayout txtlayout_matkhau = dialog.findViewById(R.id.txtlayout_matkhau);

        TextInputEditText txt_name = dialog.findViewById(R.id.txt_name);
        TextInputEditText txt_taikhoan = dialog.findViewById(R.id.txt_taikhoan);
        TextInputEditText txt_matkhau = dialog.findViewById(R.id.txt_matkhau);

        Button btn_tao = dialog.findViewById(R.id.btn_tao);
        Button btn_thoat = dialog.findViewById(R.id.btn_thoat);

        anh_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img = anh_user;
                Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if(pickPhotoIntent.resolveActivity(getPackageManager()) != null){
                    resultLauncher.launch(pickPhotoIntent);
                }else{
                    Toast.makeText(Dangnhap2.this, "These is no app that support this action !", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_tao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name =  txtlayout_name.getEditText().getText().toString().trim();
                String TaiKhoan =  txtlayout_taikhoan.getEditText().getText().toString().trim();
                String MatKhau = txtlayout_matkhau.getEditText().getText().toString().trim();
                if(Name.isEmpty() || TaiKhoan.isEmpty() || MatKhau.isEmpty()){
                    if(Name.isEmpty()) txtlayout_name.setError("tên không dduwwocj để trống");
                    else{
                        txtlayout_name.setError(null);
                        txtlayout_name.setErrorEnabled(false);
                    }
                    if(TaiKhoan.isEmpty()) txtlayout_taikhoan.setHelperText("Tài khoản không được để trống");
                    else{
                        txtlayout_taikhoan.setError(null);
                        txtlayout_taikhoan.setErrorEnabled(false);
                    }
                    if(MatKhau.isEmpty()) txtlayout_matkhau.setHelperText("Mật khẩu không được để trống");
                    else{
                        txtlayout_matkhau.setError(null);
                        txtlayout_matkhau.setErrorEnabled(false);
                    }
                }
                else{
                    Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    Btn_click.DangKi(dialog,Dangnhap2.this,txt_name.getText().toString(),txt_taikhoan.getText().toString(),txt_matkhau.getText().toString(),byteArray);
                }
            }
        });

        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }
}