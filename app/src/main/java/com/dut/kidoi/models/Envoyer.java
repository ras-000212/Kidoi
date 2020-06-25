package com.dut.kidoi.models;

public class Envoyer extends Transaction {
    public Envoyer(String username, String message, float montant, boolean fait) {
        super(username, message, montant, fait);
    }

}
