package com.mibaldi.loanmanagement.domain.features.Auth;


import android.content.Context;
import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.loanmanagement.data.repositories.LoginRepository;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;
import com.mibaldi.loanmanagement.domain.callbacks.MyError;

import javax.inject.Inject;

public class AuthInteractorImpl implements AuthInteractor {

    public LoginRepository loginRepository;


    @Inject
    public AuthInteractorImpl(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }
    @Override
    public void init(Context context, CallbackListener<FirebaseUser> listener, int type) {
        loginRepository.init(context,listener,type);
    }

    public void onActivityResult(Intent data) {
        loginRepository.signInResult(data);
    }

    public void login() {
        loginRepository.signIn();
    }

    public void logout(final CallbackListener<FirebaseUser> listener){
        loginRepository.signOut(listener);
    }

    public void addAuthState() {
        loginRepository.addAuthState();
    }

    public void removeAuthState() {
        loginRepository.removeAuthState();

    }

    @Override
    public void apiConect(CallbackListener<FirebaseUser> listener) {
        loginRepository.apiConnect(listener);
    }
}
