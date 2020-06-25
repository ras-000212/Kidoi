package com.dut.kidoi.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.dut.kidoi.LoginActivity;
import com.dut.kidoi.R;
import com.dut.kidoi.models.Transaction;
import com.dut.kidoi.models.User;
import com.dut.kidoi.repositories.FirebaseRepository;
import com.dut.kidoi.utils.Callback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private String uName;
    private FirebaseRepository fr = FirebaseRepository.getInstance();
    private HashMap<String, Transaction> testHasssss;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView logout = root.findViewById(R.id.btn_logout);
        logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            FirebaseRepository.getInstance().reset();
            Intent in = new Intent(getActivity(), LoginActivity.class);
            startActivity(in);
        });

        final TextView sessionName = root.findViewById(R.id.session_name);
        //sessionName.setText(FirebaseRepository.getInstance().getConnectedUser().getLogin());

        /**
         * appel des affichages à compléter
         */
        showARecevoir(root);
        showAEnvoyer(root);


        fr.getRecevoir("alexis", new Callback<HashMap<String, Transaction>>() {
            @Override
            public void call(HashMap<String, Transaction> stringTransactionHashMap) {

                testHasssss = stringTransactionHashMap;
                for (Map.Entry m : stringTransactionHashMap.entrySet()) {
                    Log.d("clé: "," testttttt" + m.getKey()
                            + " | valeur: " + m.getValue());
                }
            }
        });
        //User user = FirebaseRepository.getInstance().getConnectedUser();

        fr.getRecevoir(fr.getConnectedUser().getLogin(), new Callback<HashMap<String, Transaction>>() {
            @Override
            public void call(HashMap<String, Transaction> stringTransactionHashMap) {
                LinearLayout lay_parent = root.findViewById(R.id.lay_parentRecevoir) ;
                for (Map.Entry<String,Transaction> m: stringTransactionHashMap.entrySet()){
                    Log.d("map", "call: "+m.toString());
                    LinearLayout lay_child = new LinearLayout(root.getContext());
                    lay_child.setOrientation(LinearLayout.VERTICAL);

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            500, ViewGroup.LayoutParams.MATCH_PARENT);
                    layoutParams.setMargins(100,0,0,0);
                    lay_child.setBackgroundColor(getResources().getColor(R.color.yellow));
                    lay_child.setLayoutParams(layoutParams);
                    TextView tv_title = new TextView(root.getContext());
                    tv_title.setTextSize(20);
                    tv_title.setText(m.getValue().getAmi());

                    TextView tv_show = new TextView(root.getContext());
                    tv_show.setTextSize(15);
                    tv_show.setText(String.valueOf(m.getValue().getMontant()));

                    lay_child.addView(tv_title);
                    lay_child.addView(tv_show);

                    lay_parent.addView(lay_child);


                }
            }
        });

        fr.getEnvoyer(fr.getConnectedUser().getLogin(), new Callback<HashMap<String, Transaction>>() {
            @Override
            public void call(HashMap<String, Transaction> stringTransactionHashMap) {
                LinearLayout lay_parent = root.findViewById(R.id.lay_parentEnvoyer) ;
                for (Map.Entry<String,Transaction> m: stringTransactionHashMap.entrySet()){
                    Log.d("map", "call: "+m.toString());
                    LinearLayout lay_child = new LinearLayout(root.getContext());
                    lay_child.setOrientation(LinearLayout.VERTICAL);

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            500, ViewGroup.LayoutParams.MATCH_PARENT);
                    layoutParams.setMargins(100,0,0,0);
                    lay_child.setBackgroundColor(getResources().getColor(R.color.yellow));
                    lay_child.setLayoutParams(layoutParams);
                    TextView tv_title = new TextView(root.getContext());
                    tv_title.setTextSize(20);
                    tv_title.setText(m.getValue().getAmi());

                    TextView tv_show = new TextView(root.getContext());
                    tv_show.setTextSize(15);
                    tv_show.setText(String.valueOf(m.getValue().getMontant()));

                    lay_child.addView(tv_title);
                    lay_child.addView(tv_show);

                    lay_parent.addView(lay_child);


                }
            }
        });


        return root;
    }

    public void showARecevoir(View v){

        LinearLayout lay_parent = v.findViewById(R.id.lay_parentRecevoir) ;

    }

    public void showAEnvoyer(View v){

        LinearLayout lay_parent = v.findViewById(R.id.lay_parentEnvoyer) ;

    }

    public void getAllTransaction(){

        //récupérer l'objet User
    }
}
