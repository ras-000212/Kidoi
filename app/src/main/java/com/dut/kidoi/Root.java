package com.dut.kidoi;

import android.os.Bundle;

import com.dut.kidoi.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dut.kidoi.ui.chercher.ChercherFragment;
import com.dut.kidoi.ui.demander.DemanderFragment;
import com.dut.kidoi.ui.envoyer.EnvoyerFragment;
import com.dut.kidoi.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Root extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new HomeFragment();
        fragmentTransaction.add(R.id.nav_host_fragment, fragment);
        fragmentTransaction.commit();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_demander, R.id.navigation_envoyer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);




    }

}
