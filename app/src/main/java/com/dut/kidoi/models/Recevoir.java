package com.dut.kidoi.models;

import java.util.Date;

public class Recevoir extends Transaction {
    public Recevoir(String username, String message, Long montant, boolean fait) {
        super(username, message, montant, fait);
    }


}
