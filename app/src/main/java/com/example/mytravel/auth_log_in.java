package com.example.mytravel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class auth_log_in extends AppCompatActivity {
    EditText et_username,et_password;
    TextView sign_up;
    CheckBox remember_me;
    Button sign_in;

    boolean isAllFieldsChecked = false;

    FirebaseModel firebaseModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_log_in);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        sign_in = findViewById(R.id.Sign_in_Button);
        remember_me = findViewById(R.id.check_remember_me);
        sign_up = findViewById(R.id.no_account_sign_in);
        firebaseModel= new FirebaseModel();

        sign_in.setOnClickListener(v-> {
            String username = et_username.getText().toString().trim();
            String password = et_password.getText().toString().trim();
            if(isValidInput(username,password)){
                signIn_user(username,password);
            }
        });


}
    private boolean isValidInput(String username,String password){
        if(username.isEmpty()|| username.length()<4){
            et_username.setError("This Field is required");
            return false;
        }
        if(password.isEmpty()|| password.length()<4){
            et_password.setError("Invalid password");
            return false;
        }

        return true;
    }
private void signIn_user(String email,String password){
    AlertDialog alertDialog = new Progress().createDialog(this,"Loading");
    alertDialog.show();
        firebaseModel.getAuth().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                           @Override
                                           public void onComplete(@NonNull Task<AuthResult> task) {
                                               if(task.isSuccessful()){
                                                   alertDialog.dismiss();
                                                   Intent main_intent = new Intent(auth_log_in.this,MainActivity.class);
                                                   Toast.makeText(auth_log_in.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                                                   main_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                   startActivity(main_intent);
                                           }
                                       }


}).addOnFailureListener(e -> {
    alertDialog.dismiss();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });
}}
