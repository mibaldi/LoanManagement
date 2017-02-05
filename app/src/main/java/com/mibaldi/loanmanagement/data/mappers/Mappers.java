package com.mibaldi.loanmanagement.data.mappers;

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
}
