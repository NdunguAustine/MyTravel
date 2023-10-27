package com.example.mytravel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.regex.Pattern;

public class Sign_Up extends AppCompatActivity {
    EditText et_username,et_email,et_password,et_re_enter_password;
    Button register;
    TextView already_a_user;
    ImageButton facebook,google;
    FirebaseModel firebaseModel;

    boolean isAllFieldsChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_username = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_re_enter_password = findViewById(R.id.et_reEnter_Password);
        register = findViewById(R.id.registerButton);
        already_a_user = findViewById(R.id.no_account_sign_in);
        facebook = findViewById(R.id.facebook_image_button);
        google = findViewById(R.id.google_image_button);
        firebaseModel=new FirebaseModel();


        register.setOnClickListener(v->{
            String username = et_username.getText().toString().trim();
            String email = et_email.getText().toString().trim();
            String password = et_password.getText().toString().trim();
            String re_enter_password = et_re_enter_password.getText().toString().trim();
            if(isValidInput(username,email,password,re_enter_password)){
                UserModel userModel=new UserModel(username,email,password);
                register_user(userModel);
            }
        });

    }
    private boolean isValidInput(String username,String email,String password,String re_enter_password){
        if(username.isEmpty()|| username.length()<3){
            et_username.setError("This Field is required");
            return false;
        }
        if(email.isEmpty()){
            et_email.setError("This field is required");
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            et_email.setError("Invalid Email!");
            return false;
        }
        if(password.isEmpty()|| password.length()<6){
            et_password.setError("Weak password");
            return false;
        }
        if(re_enter_password.isEmpty()){
            et_re_enter_password.setError("This field is required");
            return false;
        }
        if(!password.equals(re_enter_password)){
            et_re_enter_password.setError("password not matching!");
            return false;
        }


        return true;
    }
    private void register_user(UserModel userModel){
        firebaseModel.getAuth().createUserWithEmailAndPassword(userModel.getEmail(),userModel.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent main_intent = new Intent(Sign_Up.this,MainActivity.class);
                            Toast.makeText(Sign_Up.this, "Sign up Successful", Toast.LENGTH_SHORT).show();
                            main_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(main_intent);
                        }
                    }
                }).addOnFailureListener(e->{
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

}