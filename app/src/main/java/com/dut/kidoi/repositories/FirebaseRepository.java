package com.dut.kidoi.repositories;

import android.telecom.Call;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dut.kidoi.models.Transaction;
import com.dut.kidoi.models.User;
import com.dut.kidoi.utils.Callback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class FirebaseRepository {

    private static FirebaseRepository instance;
    private FirebaseFirestore db;
    private User connectedUser;

    static {
        instance = new FirebaseRepository();
    }

    public FirebaseRepository() {
        db = FirebaseFirestore.getInstance();
    }

    public static FirebaseRepository getInstance() {
        return instance;
    }

    public void addUser(String email, String username, String uID, Callback<User> cb) {
        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("username", username);
        user.put("userID", uID);

        db.collection("users").document(username)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("", "DocumentSnapshot added with ID: ");
                        if (cb != null)
                            getUser(uID, cb);
                    }
                })
                .addOnFailureListener(e -> {
                    Log.w("", "Error adding document", e);
                    if(cb != null)
                        cb.call(null);
                });
    }

    public void updateUser(User user) {
        db.collection("users").document(user.getDocumentId())
                .update("email", user.getEmail(), "username", user.getLogin(), "userID", user.getDocumentId());
    }

    public void getUser(String uuid, Callback<User> cb) {
        db.collection("users").whereEqualTo("userID", uuid).get().addOnCompleteListener(task -> {
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

    public void demander (String demandeur, String receveur, int montant, String message, Boolean deuxiemeTransaction){
        Map<String, Object> transaction = new HashMap<>();
        transaction.put("idDemandeur", demandeur);
        transaction.put("idReceveur", receveur);
        transaction.put("montant", montant);
        transaction.put("fait",false);
        transaction.put("message",message);

        StringBuilder id = new StringBuilder();
        Random rnd = new Random();
        id.append(demandeur+receveur+montant+message+rnd.nextInt(10000));

        //Ajout dans le demandeur
        db.collection("users").document(demandeur).collection("recevoir").document(id.toString()).set(transaction)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("Success", "bien joue");
               if (deuxiemeTransaction==false){
                   envoyer(receveur,demandeur,montant,message,true);
               }

            }
        })      .addOnFailureListener(e -> {
                    Log.w("Error", "Error adding document", e);

                });
    }
    public void envoyer (String envoyeur, String receveur, int montant, String message,boolean deuxiemeTransaction){
        Map<String, Object> transaction = new HashMap<>();
        transaction.put("idDemandeur", envoyeur);
        transaction.put("idReceveur", receveur);
        transaction.put("montant", montant);
        transaction.put("fait",false);
        transaction.put("message",message);

        StringBuilder id = new StringBuilder();
        Random rnd = new Random();
        id.append(envoyeur+receveur+montant+message+rnd.nextInt(10000));

        db.collection("users").document(envoyeur).collection("envoyer").document(id.toString()).set(transaction)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Success", "bien joue");
                        if(deuxiemeTransaction==false)
                            demander(receveur,envoyeur,montant,message,true);

                    }
                })      .addOnFailureListener(e -> {
            Log.w("Error", "Error adding document", e);

        });
    }

public String generateID(){

        return "";
}

    public void getUser(Callback<User> cb){
        getUser(FirebaseAuth.getInstance().getCurrentUser().getUid(), user -> {
            connectedUser = user;
            cb.call(connectedUser);
        });
    }

    public User getConnectedUser() {
        return connectedUser;
    }

    public void reset(){
        connectedUser = null;
    }

    public void transaction(String credit,String debit,String montant,String motif){
        //db.collection("users").whereEqualTo("username", credit).get().add
    }


}
