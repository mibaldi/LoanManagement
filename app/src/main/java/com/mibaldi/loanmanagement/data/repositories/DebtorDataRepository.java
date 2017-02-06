package com.mibaldi.loanmanagement.data.repositories;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mibaldi.loanmanagement.data.mappers.Mappers;
import com.mibaldi.loanmanagement.data.models.Debtor;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;
import com.mibaldi.loanmanagement.domain.callbacks.ErrorConstants;
import com.mibaldi.loanmanagement.domain.callbacks.MyError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DebtorDataRepository implements Serializable {
    private final FirebaseAuth mAuth;
    private final String userKey;
    FirebaseDatabase instance = FirebaseDatabase.getInstance();
    DatabaseReference debtors = instance.getReference("Debtors");
    DatabaseReference usersDebtors = instance.getReference("UsersDebtors");
    DatabaseReference root = instance.getReference();
    Debtor currentDebtor = null;


    @Inject
    public DebtorDataRepository(){
        mAuth = FirebaseAuth.getInstance();
        userKey = mAuth.getCurrentUser().getUid();
    }

    public void createDebtor(Map<String,Object> dataObject, final CallbackListener<String> listener) {
        final String debtorKey = debtors.push().getKey();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Debtors/" + debtorKey, dataObject);
        childUpdates.put("/UsersDebtors/" + userKey + "/" + debtorKey, true);
        root.updateChildren(childUpdates).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                listener.onSuccess(debtorKey);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                MyError myError = new MyError();
                myError.setError(ErrorConstants.ERROR_400);
                myError.setDescription(e.getMessage());
                listener.onError(myError);
            }
        });
    }
    public void modifyDebtor(String key,Map<String,Object> dataObject, final CallbackListener<Boolean> listener) {
        debtors.child(key).updateChildren(dataObject).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                listener.onSuccess(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                MyError error = new MyError();
                error.setDescription(e.getMessage());
                error.setError(ErrorConstants.ERROR_400);
                listener.onError(error);
            }
        });
    }

    public void getDebtorList(final CallbackListener<List<Debtor>> listener){
        final List<Debtor> list = new ArrayList<>();
        usersDebtors.child(userKey).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               Map<String, Object> objectMap = (HashMap<String, Object>) dataSnapshot.getValue();
               for (Map.Entry<String,Object> entry: objectMap.entrySet()){
                   String key = entry.getKey();
                   getDetailDebtor(key, list, listener);
               }
           }
           @Override
           public void onCancelled(DatabaseError databaseError) {
               Log.d("REPOCacelled",databaseError.getDetails());
           }
       });
    }

    private void getDetailDebtor(String key, final List<Debtor> list, final CallbackListener<List<Debtor>> listener) {
        debtors.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Debtor debtor = Mappers.MapToDebtor(dataSnapshot);
                list.add(debtor);
                listener.onSuccess(list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("REPOCacelled2",databaseError.getDetails());
            }
        });
    }
    public void getDetailDebtor(String key, final CallbackListener<Debtor> listener) {
        debtors.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Debtor debtor = Mappers.MapToDebtor(dataSnapshot);
                listener.onSuccess(debtor);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("REPOCacelled2",databaseError.getDetails());
            }
        });
    }

    public Debtor getCurrentDebtor() {
        return currentDebtor;
    }

    public void setCurrentDebtor(Debtor currentDebtor) {
        this.currentDebtor = currentDebtor;
    }
}
