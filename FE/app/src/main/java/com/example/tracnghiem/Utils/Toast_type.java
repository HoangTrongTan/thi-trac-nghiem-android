package com.example.tracnghiem.Utils;

import android.content.Context;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.tracnghiem.Activitys.CauHoi;
import com.example.tracnghiem.R;

public class Toast_type {
    public static void Toast_Style(Context context, int anh,int duration){
        Toast toast = new Toast(context);
        LayoutInflater inflater =LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.layout_toast,null);
        ImageView image = view.findViewById(R.id.anh);
        image.setImageDrawable(ContextCompat.getDrawable(context,anh));
        toast.setView(view);
        toast.setGravity(Gravity.TOP,0,0);
        toast.setDuration(duration);
        toast.show();

    }

}
