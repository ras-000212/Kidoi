package com.dut.kidoi.models;

import java.net.PasswordAuthentication;

public class User {

    private String login;
    private String email;
    private String uID;


    public User(String login, String email,String uID){
        this.email = email;
        this.login = login;
        this.uID=uID;
    }

    public String getLogin(){

        return login;
    }

    public String getEmail(){

        return email;
    }

    public String getuID() {
        return uID;
    }
}
