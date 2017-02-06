package com.mibaldi.loanmanagement.data.mappers;

import com.google.firebase.database.DataSnapshot;
import com.mibaldi.loanmanagement.data.models.Debtor;

import java.util.HashMap;
import java.util.Map;

public class Mappers {
    public static Map<String,Object> DebtorToMap(Debtor debtor){
        Map<String,Object> map = new HashMap<>();
        map.put("name",debtor.getName());
        map.put("email",debtor.getEmail());
        return map;
    }
    public static Debtor MapToDebtor(DataSnapshot dataSnapshot){
        Debtor debtor = new Debtor();
        debtor.setId(dataSnapshot.getKey());
        debtor.setName(dataSnapshot.child("name").getValue().toString());
        debtor.setEmail(dataSnapshot.child("email").getValue().toString());
        return debtor;
    }
}
