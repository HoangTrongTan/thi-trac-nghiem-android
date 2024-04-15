package com.example.tracnghiem.CustomTextView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class RB extends AppCompatTextView {
    public RB(@NonNull Context context) {
        super(context);
        setFontTextView();
    }

    public RB(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFontTextView();
    }

    public RB(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFontTextView();
    }
    private void setFontTextView(){
        Typeface face = Utills.getRB(getContext());
        setTypeface(face);
    }
}
