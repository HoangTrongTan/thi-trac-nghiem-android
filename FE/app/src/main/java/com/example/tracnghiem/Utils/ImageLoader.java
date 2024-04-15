package com.example.tracnghiem.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageLoader {
    public static void loadImage(Context context, Uri uri, ImageView imageView){
        Glide.with(context)
                .load(uri)
                .into(imageView);
    }
    public static Uri getImageformUri(Context context, Intent data){
        return data.getData();
    }
}
