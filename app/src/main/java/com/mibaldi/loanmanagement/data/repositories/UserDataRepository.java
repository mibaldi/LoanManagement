package com.mibaldi.loanmanagement.data.repositories;


import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;
import com.mibaldi.loanmanagement.domain.callbacks.ErrorConstants;
import com.mibaldi.loanmanagement.domain.callbacks.MyError;

import java.io.Serializable;
import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserDataRepository implements Serializable {
    FirebaseDatabase instance = FirebaseDatabase.getInstance();
    DatabaseReference users = instance.getReference("Users");
    public void getUser(CallbackListener<String> listener) {
            if (false){
                listener.onSuccess("nombre");
            }else {
                MyError myError = new MyError();
                myError.setError(ErrorConstants.ERROR_400);
                myError.setDescription("nombre ERROR");
                listener.onError(myError);
            }
    }
    @Inject
    public UserDataRepository(){

    }

    public void createUser(FirebaseUser result, final CallbackListener<Boolean> callbackListener) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("id",result.getUid());
        hashMap.put("name",result.getDisplayName());
        hashMap.put("email",result.getEmail());
        users.child(result.getUid()).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                callbackListener.onSuccess(task.isSuccessful());
            }
        });
    }
}
