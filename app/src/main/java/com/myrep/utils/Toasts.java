package com.myrep.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Michael Brennan on 8/25/15.
 */
public class Toasts {

    public static void shortToast(Context context, String message){
        Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT)
                .show();
    }

    public static void longToast(Context context, String message){
        Toast.makeText(
                context,
                message,
                Toast.LENGTH_LONG)
                .show();
    }
}
