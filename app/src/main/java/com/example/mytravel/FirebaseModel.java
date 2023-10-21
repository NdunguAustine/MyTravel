package com.example.mytravel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseModel {
    FirebaseAuth auth;
    FirebaseUser currentUser;
    public FirebaseModel(){
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

    }
    public FirebaseAuth getAuth(){
        return auth;
    }
    public FirebaseUser getCurrentUser(){
        return currentUser;
    }
    public boolean isUser(){
        return currentUser != null;
    }
}
