package com.dut.kidoi.models;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.dut.kidoi.Demander;
import com.dut.kidoi.Profil;
import com.dut.kidoi.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Envoyer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envoyer);

        BottomNavigationView nav = findViewById(R.id.bottom_nav);
        nav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    //Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.action_demander:
                            startActivity(new Intent(Envoyer.this, Demander.class));
                            break;
                        case R.id.action_envoyer:
                            startActivity(new Intent(Envoyer.this, Envoyer.class));
                            break;
                        case R.id.action_chercher:
                            startActivity(new Intent(Envoyer.this, Chercher.class));
                            break;
                        case R.id.action_profil:
                            startActivity(new Intent(Envoyer.this,Profil.class));
                            break;
                    }
                    //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };
}
