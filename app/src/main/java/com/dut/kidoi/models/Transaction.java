package com.dut.kidoi.models;

import com.dut.kidoi.repositories.FirebaseRepository;
import com.dut.kidoi.utils.Callback;

import java.util.Date;

public class Transaction {

    private User ami;
    private String message;
    private float montant;
    private boolean fait;
    FirebaseRepository fr = new FirebaseRepository();

    public Transaction(String username, String message, float montant, boolean fait) {

        this.fait = fait;
        this.message = message;
        this.montant = montant;
        this.ami = null;
        fr.getUserLogin(username, new Callback<User>() {
            private User u;

            @Override
            public void call(User user) {
                String login = user.getLogin();
                String email = user.getEmail();
                String documentID = user.getDocumentId();

                u=new User(login,email,documentID);
                setAmi(u);
            }

        });


    }

    public float getMontant() {
        return montant;
    }

    public boolean isFait() {
        return fait;
    }

    public void setAmi(User u ){this.ami=u;}
}
