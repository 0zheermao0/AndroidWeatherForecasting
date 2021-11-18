package edu.neu.weatherforecasting.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static Toast mToast;

    @SuppressLint("ShowToast")
    public static void showMsg(Context context, String msg){
        if(mToast == null){
            mToast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        }else {
            mToast.setDuration(Toast.LENGTH_LONG);
            mToast.setText(msg);
        }
        mToast.show();
    }
}
