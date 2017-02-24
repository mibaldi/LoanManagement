package com.mibaldi.loanmanagement.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;



public final class FirebaseReferences {

    public static FirebaseDatabase firebaseInstance = FirebaseDatabase.getInstance();

    public static String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    public FirebaseReferences(){}

    public static Query getDebtorsReferences(){
        return firebaseInstance.getReference("Debtors");
    }

    public static Query getUsersDebtorsReferences(){
        return firebaseInstance.getReference("UsersDebtors").child(userId);
    }
    public static Query getUserReferences(){
        return firebaseInstance.getReference("Users").child(userId);
    }

}
