package com.example.tracnghiem.CustomTextView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class GS extends AppCompatTextView {
    public GS(@NonNull Context context) {
        super(context);
        setFontTextView();
    }

    public GS(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFontTextView();
    }

    public GS(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFontTextView();
    }
    private void setFontTextView(){
        Typeface type = Utills.getGS(getContext());
        setTypeface(type);
    }
}
