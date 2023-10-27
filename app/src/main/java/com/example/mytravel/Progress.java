package com.example.mytravel;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class Progress {
    public AlertDialog createDialog(Context context, String message){
        View view = LayoutInflater.from(context).inflate(R.layout.progress,null);
        TextView textviewMessage = view.findViewById(R.id.txt_loading);
        textviewMessage.setText(message);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setView(view);
        return builder.create();

    }
}
