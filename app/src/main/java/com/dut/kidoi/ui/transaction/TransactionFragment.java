package com.dut.kidoi.ui.transaction;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.BoringLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.dut.kidoi.R;
import com.dut.kidoi.models.User;
import com.dut.kidoi.repositories.FirebaseRepository;
import com.dut.kidoi.utils.Callback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.concurrent.TimeUnit;

public class TransactionFragment extends Fragment {

    private TransactionViewModel transactionViewModel;
    private Spinner sp;
    private FirebaseRepository fr = new FirebaseRepository();
    private EditText et_login, et_montant, et_message;
    private Button bt_transaction;
    private int montant;
    private String login,message, type, uName;
    private boolean validate = false;

    public static TransactionFragment newInstance() {
        TransactionFragment fragment = new TransactionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        transactionViewModel =
                ViewModelProviders.of(this).get(TransactionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_transaction, container, false);

        /**
         * récupération nom de session active
         */

            this.uName = FirebaseRepository.getInstance().getConnectedUser().getLogin();
            Log.d("success", "voici le user" + uName);


        /**
         * appel des views
         */
        this.sp = root.findViewById(R.id.sp_type);
        this.et_login = root.findViewById(R.id.et_login);
        this.et_message = root.findViewById(R.id.et_message);
        this.et_montant = root.findViewById(R.id.et_montant);
        this.bt_transaction = root.findViewById(R.id.btn_transaction);

        bt_transaction.setOnClickListener(v -> {
            intialize();
            transaction();
        });

        return root;
    }

    public void intialize(){

        this.login=this.et_login.getText().toString();
        this.message = this.et_message.getText().toString();
        this.montant = Integer.parseInt(this.et_montant.getText().toString());
        this.type = this.sp.getSelectedItem().toString();
    }

    public void transaction(){

        verifInput(new Callback<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                Log.d("boolean transaction", "call: "+aBoolean);
                if(aBoolean){
                    Log.d("Success", "il s'agit de : " + type);
                    if(type.equals("Demande")){
                        Log.d("Success", "Demande commencé avec : "+ uName + " " + login + " " + montant + " " + message);
                        fr.demander(uName, login, montant, message, false);
                        clearInput();
                        Toast.makeText(getContext(), "Demande d'argent envoyée avec succès",Toast.LENGTH_LONG).show();

                    }else{
                        Log.d("Success", "Envoie commencé avec : "+ uName + " " + login + " " + montant + " " + message);
                        fr.envoyer(uName, login, montant, message, false);
                        clearInput();
                        Toast.makeText(getContext(), "Demande d'envoie d'argent envoyée avec succès",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });


    }

    public void verifInput(Callback<Boolean>b){
        verifUserExist(this.login, new Callback<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if(login.isEmpty()){
                    et_login.setError("Aucun utilisateur avec ce login");
                    et_login.requestFocus();
                    b.call(false);
                }
                else if (aBoolean==false){
                    et_login.setError("pas de login existant");
                    et_login.requestFocus();
                    b.call(false);
                }
                else if(montant == 0){
                    et_montant.setError("Montant invalide");
                    et_montant.requestFocus();
                    b.call(false);
                }
                else if(message.isEmpty()) {
                    et_message.setError("Veuillez indiquer un message");
                    et_message.requestFocus();
                    b.call(false);
                }
                else{
                    b.call(true);
                }
            }
        });

    }

    public void clearInput(){
        et_montant.setText("");
        et_message.setText("");
        et_login.setText("");
    }

    /**
     * Verifier si le login entré est bien associé à un user existant A MODIFIER
     * @param login
     * @return
     */

    public void verifUserExist(String login, Callback<Boolean> b){
        fr.getUserLogin(login, new Callback<User>() {
            @Override
            public void call(User user) {
                if(user==null) {
                    b.call(false);
                }else b.call(true);
                }
        });
    }
}
