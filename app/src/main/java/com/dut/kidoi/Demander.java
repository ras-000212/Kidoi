package com.dut.kidoi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.dut.kidoi.models.Chercher;
import com.dut.kidoi.models.Envoyer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Demander extends AppCompatActivity {
    private Button bt_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demander);

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
                            startActivity(new Intent(Demander.this,Demander.class));
                            break;
                        case R.id.action_envoyer:
                            startActivity(new Intent(Demander.this, Envoyer.class));
                            break;
                        case R.id.action_chercher:
                            startActivity(new Intent(Demander.this, Chercher.class));
                            break;
                        case R.id.action_profil:
                            startActivity(new Intent(Demander.this,Profil.class));
                            break;
                    }
                    //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };
}
