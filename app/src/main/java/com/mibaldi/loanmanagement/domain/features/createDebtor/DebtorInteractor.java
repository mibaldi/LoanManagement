package com.mibaldi.loanmanagement.domain.features.createDebtor;

import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.loanmanagement.data.models.Debtor;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;

import java.util.Map;

public interface DebtorInteractor {
    void createDebtor(Map<String,Object> Debtor, CallbackListener<String> callbackListener);
    void modifyDebtor(String key,Map<String,Object> Debtor, CallbackListener<Boolean> callbackListener);
    void getCurrentDebtor(CallbackListener<Debtor>listener);
}
