package com.mibaldi.loanmanagement.domain.features.user;

import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.loanmanagement.data.repositories.LoginRepository;
import com.mibaldi.loanmanagement.data.repositories.UserDataRepository;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;

import javax.inject.Inject;

public class UserInteractorImpl implements UserInteractor {
    public UserDataRepository userDataRepository;
    @Inject
    public UserInteractorImpl(UserDataRepository userDataRepository){
        this.userDataRepository = userDataRepository;
    }

    @Override
    public void createUser(FirebaseUser result, CallbackListener<Boolean> callbackListener) {
        userDataRepository.createUser(result,callbackListener);
    }
}
