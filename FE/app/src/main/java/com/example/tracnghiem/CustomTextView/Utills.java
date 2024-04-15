package com.example.tracnghiem.CustomTextView;

import android.content.Context;
import android.graphics.Typeface;

public class Utills {
    private static Typeface GS;
    private static Typeface PR;
    private static Typeface RB;
    public static Typeface getGS(Context context){
        if(GS == null){
            GS = Typeface.createFromAsset(context.getAssets(),"fonts/GS.ttf");
        }
        return GS;
    }
    public static Typeface getPR(Context context){
        if(PR == null){
            PR = Typeface.createFromAsset(context.getAssets(),"fonts/PR.ttf");
        }
        return PR;
    }public static Typeface getRB(Context context){
        if(RB == null){
            RB = Typeface.createFromAsset(context.getAssets(),"fonts/HUMANOIDSTRAIGHT.TTF");
        }
        return RB;
    }
    //HUMANOIDSTRAIGHT.TTF
}
