package com.dut.kidoi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Profil extends AppCompatActivity {

    //@BindView(R.id.activity_main_bottom_navigation)BottomNavigationView bottomNavigationView;
    private BottomNavigationView B;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.B = findViewById(R.id.activity_main_bottom_navigation);
        this.configureBottomView();

    }



    private void configureBottomView(){
        B.setOnNavigationItemSelectedListener(item -> updateMainFragment(item.getItemId()));
    }

    private Boolean updateMainFragment(Integer integer){
        switch (integer) {
            case R.id.action_demander:
                startActivity(new Intent(Profil.this,Demander.class));
                break;
            case R.id.action_envoyer:
                startActivity(new Intent(Profil.this,Demander.class));
                break;
            case R.id.action_chercher:
                startActivity(new Intent(Profil.this,Demander.class));
                break;
            case R.id.action_profil:
                startActivity(new Intent(Profil.this,Demander.class));
                break;
        }
        return true;
    }
}
