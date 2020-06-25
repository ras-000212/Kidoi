package com.dut.kidoi.ui.transaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.dut.kidoi.R;
import com.dut.kidoi.repositories.FirebaseRepository;
import com.google.firebase.auth.FirebaseAuth;

public class TransactionFragment extends Fragment {

    private TransactionViewModel transactionViewModel;
    private Spinner sp;
    private FirebaseRepository fr=new FirebaseRepository();
    private EditText et_login, et_montant, et_message;
    private Button bt_transaction;
    private int montant;
    private String login,message, type;

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
         * appel des views
         */
        this.sp = root.findViewById(R.id.sp_type);
        this.et_login = root.findViewById(R.id.et_login);
        this.et_message = root.findViewById(R.id.et_message);
        this.et_montant = root.findViewById(R.id.et_montant);
        this.bt_transaction = root.findViewById(R.id.btn_transaction);

        bt_transaction.setOnClickListener(v -> {

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
        intialize();
        if(!verifInput()){
            return;
        }

        if(this.type.equals("Demande")){
            String demandeur = "" ;
            fr.demander(demandeur, login, montant, message, false);

        }else{
            String demandeur = "" ;
            fr.envoyer(demandeur, login, montant, message, false);
        }

    }

    public boolean verifInput(){

        boolean validate = true;

        if(this.login.isEmpty()){
            this.et_login.setError("Aucun utilisateur avec ce login");
            et_login.requestFocus();
            validate = false;
        }

        if(this.montant == 0){
            this.et_montant.setError("Montant invalide");
            et_montant.requestFocus();
            validate = false;
        }

        if(this.message.isEmpty()){
            this.et_message.setError("Veuillez indiquer un message");
            et_message.requestFocus();
            validate = false;
        }

        return validate;
    }
}
