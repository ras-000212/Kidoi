package com.dut.kidoi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dut.kidoi.models.Users;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private ArrayList<Users> myDataSet = new ArrayList<Users>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Users u = new Users();

        myDataSet.add(u);



    }
}
