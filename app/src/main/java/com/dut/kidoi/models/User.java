package com.dut.kidoi.models;

import android.util.Log;

import com.dut.kidoi.repositories.FirebaseRepository;
import com.dut.kidoi.utils.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {

    private FirebaseRepository fr;

    private String login;
    private String email;
    private final String documentId;
    /*private HashMap<String, Transaction> transactionRecevoir = new HashMap<>();
    private HashMap<String, Transaction> transactionEnvoyer = new HashMap<>();*/

    public User(String login, String email, String documentId) {
        this.email = email;
        this.login = login;
        this.documentId = documentId;

        /*fr = FirebaseRepository.getInstance();
        Log.d("NUMEROOO1","TON NOM BATARD" + this.login);

        fr.getRecevoir(this.login, new Callback<HashMap<String, Transaction>>() {
            @Override
            public void call(HashMap<String, Transaction> stringTransactionHashMap) {
                transactionRecevoir = stringTransactionHashMap;

                for (Map.Entry m : stringTransactionHashMap.entrySet()) {
                    Log.d("clé: "," testttttt" + m.getKey()
                            + " | valeur: " + m.getValue());
                }
            }
        });*/
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void save(){
        FirebaseRepository.getInstance().updateUser(this);
    }

}
