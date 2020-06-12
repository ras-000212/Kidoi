package com.dut.kidoi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
/*import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;*/

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dut.kidoi.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private ArrayList<User> myDataSet = new ArrayList<User>();
    private TextView link_connect;
    private String email,password;
    private FirebaseAuth mAuth;
    private EditText et_email,et_password;
    private Button btn_connexion;


    private void initialize(){
        et_email =findViewById(R.id.et_email);
        email=et_email.getText().toString();
        et_password= findViewById(R.id.et_pwd);
        password=et_password.getText().toString();
        btn_connexion = findViewById(R.id.btn_connexion);
        link_connect = findViewById(R.id.tv_inscription);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

    }

    private void updateUI(FirebaseUser currentUser) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        link_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUp.class));
            }
        });

        btn_connexion.setOnClickListener(v -> {
            signIn();
        });




    }

    public void signIn (){
        initialize();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Authentication", "Success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                            Intent in = new Intent(LoginActivity.this, Profil.class);
                            startActivity(in);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Authentification", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                });
    }


    
    
}
