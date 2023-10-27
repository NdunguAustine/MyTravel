package com.example.mytravel;

public class UserModel {
    String userName,email,password;
    public UserModel(){

    }
    public UserModel(String userName, String email, String password){
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserName(){
        return userName;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
}
