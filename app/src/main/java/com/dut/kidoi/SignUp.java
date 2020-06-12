package com.dut.kidoi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dut.kidoi.models.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    private EditText et_email,et_pwd,et_conpwd,et_user;
    private String email,pwd,conPwd,username;
    private Button btn_inscription;
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private Firebase db;

    public void initializeUser(){
        et_email = findViewById(R.id.et_email);
        et_pwd=findViewById(R.id.et_pwd);
        et_conpwd=findViewById(R.id.et_pwdConfirm);
        et_user=findViewById(R.id.et_user);

        email=et_email.getText().toString();
        pwd=et_pwd.getText().toString();
        conPwd=et_conpwd.getText().toString();
        username=et_user.getText().toString();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_inscription = findViewById(R.id.btn_inscription);


        btn_inscription.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(SignUp.this, Root.class));
                initializeUser();
                createAccount();
            }

        });
    }

    public void createAccount(){
        mAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("SignUp", "Success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            String uID = user.getUid();
                            updateUI(user);
                            db.addUser(email,username,uID);
                            FirebaseAuth.getInstance().signOut();
                            signIn();


                        } else {
                            Toast.makeText(SignUp.this,"L'adresse mail que vous tentez d'utiliser est déjà attribuée à un compte", Toast.LENGTH_SHORT).show();
                        }
                    }

                    private void updateUI(FirebaseUser user) {
                    }
                });
    }

    //check if the form is valid
    public boolean validate(){
        boolean valid = true;
        if(username.isEmpty()){
            et_user.setError("Login manquant");
            et_user.requestFocus();
            valid =false;
        }

        else if(pwd.length()<6){
            et_pwd.setError("Le mot de passe doit contenir au moins 6 caractères");
            et_pwd.requestFocus();
            valid =false;
        }
        else if(conPwd.isEmpty()) {
            et_conpwd.setError("Confirmez votre mot de passe");
            et_conpwd.requestFocus();
            valid =false;
        }
        else if (!pwd.equals(conPwd)){
            et_conpwd.setError("Les mots de passe ne correspondent pas ");
            et_conpwd.requestFocus();
            valid =false;
        }
        else if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            et_email.setError("Email manquant/incorrect");
            et_email.requestFocus();
            valid =false;
        }

        return valid;
    }

    public void signIn (){
        mAuth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Authentication", "Success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                            Intent in = new Intent(SignUp.this, Profil.class);
                            startActivity(in);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Authentification", "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }

                    private void updateUI(Object o) {
                    }
                });
    }


}
