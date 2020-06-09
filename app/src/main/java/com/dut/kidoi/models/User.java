package com.dut.kidoi.models;

import java.net.PasswordAuthentication;

public class User {

    private String login;
    private String email;


    public User(String login, String email){
        this.email = email;
        this.login = login;
    }

    public String getLogin(){

        return login;
    }

    public String getEmail(){

        return email;
    }
}
