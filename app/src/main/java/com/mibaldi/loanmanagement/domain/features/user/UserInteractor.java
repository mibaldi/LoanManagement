package com.mibaldi.loanmanagement.domain.features.user;

import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;

public interface UserInteractor {
    void createUser(FirebaseUser result, CallbackListener<Boolean> callbackListener);
}
