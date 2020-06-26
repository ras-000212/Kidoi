package com.dut.kidoi.ui.chercher;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.dut.kidoi.R;
import com.dut.kidoi.models.Transaction;
import com.dut.kidoi.models.User;
import com.dut.kidoi.repositories.FirebaseRepository;
import com.dut.kidoi.utils.Callback;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class ChercherFragment extends Fragment {

    private ChercherViewModel chercherViewModel;
    private EditText et_searchUser;
    private Button btn_searchUser;
    private FirebaseRepository fr=new FirebaseRepository();
    private FirebaseFirestore db;


    public static ChercherFragment newInstance() {
        ChercherFragment fragment = new ChercherFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        chercherViewModel =
                ViewModelProviders.of(this).get(ChercherViewModel.class);
        View root = inflater.inflate(R.layout.fragment_chercher, container, false);

        et_searchUser=root.findViewById(R.id.et_searchUser);
        btn_searchUser=root.findViewById(R.id.btn_searchUser);


        Log.d("CHERCHER FRAGMENT", "VU CREEE");

        btn_searchUser.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String name_friend = et_searchUser.getText().toString();
              Log.d("ecriture", "onCreateView: " + name_friend);
              /*fr.getUserLogin(name_friend, new Callback<User>() {
                  @Override
                  public void call(User user) {
                      Log.d("USER", user.toString() + user.getLogin() + user.getEmail());
                  }
              });
              //fr.demander("alexis","dede",5,"gibus",false);
                fr.getRecevoir("dede", new Callback<HashMap<String, Transaction>>() {
                    @Override
                    public void call(HashMap<String, Transaction> stringTransactionHashMap) {

                    }
                });

                fr.getEnvoyer("alexis", new Callback<HashMap<String, Transaction>>() {
                    @Override
                    public void call(HashMap<String, Transaction> stringTransactionHashMap) {

                    }
                });*/
            // fr.deleteTransaction("alexis","alexisdede100que du biffff5513","envoyer");
          }
      });

        /*final TextView textView = root.findViewById(R.id.text_dashboard);
        chercherViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;

    }




    public void getUserLogin(String username, Callback<User> cb) {
        db.collection("users").whereEqualTo("username", username).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot document = task.getResult();

                if (document.getDocuments().isEmpty()) {
                    cb.call(null);
                    return;
                }

                cb.call(new User(
                        document.getDocuments().get(0).getString("username"),
                        document.getDocuments().get(0).getString("email"),
                        document.getDocuments().get(0).getString("userID")
                ));
            }
        });
    }

    }