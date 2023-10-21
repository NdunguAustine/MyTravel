package com.example.mytravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class auth_log_in extends AppCompatActivity {
    EditText username,password;
    TextView sign_up;
    CheckBox remember_me;
    Button sign_in;

    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_log_in);

        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        sign_in = findViewById(R.id.Sign_in_Button);
        remember_me = findViewById(R.id.check_remember_me);
        sign_up = findViewById(R.id.no_account_sign_in);


}
}