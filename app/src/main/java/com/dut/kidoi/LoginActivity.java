package com.dut.kidoi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
import com.dut.kidoi.models.Users;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private ArrayList<Users> myDataSet = new ArrayList<Users>();

    private TextView link_connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        link_connect = findViewById(R.id.tv_inscription);

        /*
            Passer de la page de connexion à celle d'inscription
         */
        link_connect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(LoginActivity.this, SignUp.class));
            }

        });
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Users u = new Users();

        myDataSet.add(u);



    }
}
