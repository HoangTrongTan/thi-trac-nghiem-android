package com.example.tracnghiem.CustomTextView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class PR extends AppCompatTextView {
    public PR(@NonNull Context context) {
        super(context);
        setFontTextView();
    }

    public PR(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFontTextView();
    }

    public PR(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFontTextView();
    }
    private void setFontTextView(){
        Typeface type = Utills.getPR(getContext());
        setTypeface(type);
    }
}
