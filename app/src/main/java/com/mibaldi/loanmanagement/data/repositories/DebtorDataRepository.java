package com.mibaldi.loanmanagement.data.repositories;


import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mibaldi.loanmanagement.data.models.Debtor;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;
import com.mibaldi.loanmanagement.domain.callbacks.ErrorConstants;
import com.mibaldi.loanmanagement.domain.callbacks.MyError;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DebtorDataRepository implements Serializable {
    private final FirebaseAuth mAuth;
    FirebaseDatabase instance = FirebaseDatabase.getInstance();
    DatabaseReference debtors = instance.getReference("Debtors");
    DatabaseReference root = instance.getReference();
    Debtor currentDebtor = null;

    @Inject
    public DebtorDataRepository(){
        mAuth = FirebaseAuth.getInstance();
    }

    public void createDebtor(Map<String,Object> dataObject, final CallbackListener<String> listener){
        final String debtorKey = debtors.push().getKey();
        final String userKey = mAuth.getCurrentUser().getUid();

        Map<String,Object> childUpdates = new HashMap<>();
        childUpdates.put("/Debtors/"+debtorKey,dataObject);
        childUpdates.put("/UsersDebtors/"+userKey+"/"+debtorKey,true);
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



}
