package com.mibaldi.loanmanagement.data.repositories;


import android.content.Context;
import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.loanmanagement.data.managers.GoogleLoginManager;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;
import com.mibaldi.loanmanagement.utils.Constants;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LoginRepository {

    public GoogleLoginManager googleLoginManager;
    private int type;

    @Inject
    public LoginRepository(GoogleLoginManager googleLoginManager){
        this.googleLoginManager = googleLoginManager;
    }
    public void init(Context context, CallbackListener<FirebaseUser> listener, int type) {
        this.type = type;
        if (type == Constants.GOOGLE_SIGN_IN){
            googleLoginManager.init(context,listener);
        }
        else if (type == Constants.FB_SIGN_IN){

        }
    }
    public void signIn(){
        if (type == Constants.GOOGLE_SIGN_IN){
            googleLoginManager.signIn();
        }
        else if (type == Constants.FB_SIGN_IN){

        }
    }
    public void signOut(CallbackListener dataResultListener){
        if (type == Constants.GOOGLE_SIGN_IN){
            googleLoginManager.signOut(dataResultListener);
        }
        else if (type == Constants.FB_SIGN_IN){

        }
    }
    public void addAuthState(){
        if (type == Constants.GOOGLE_SIGN_IN){
            googleLoginManager.addAuthState();
        }
        else if (type == Constants.FB_SIGN_IN){

        }
    }
    public void removeAuthState(){
        if (type == Constants.GOOGLE_SIGN_IN){
            googleLoginManager.removeAuthState();
        }
        else if (type == Constants.FB_SIGN_IN){

        }
    }
    public void signInResult(Intent data){
        if (type == Constants.GOOGLE_SIGN_IN){
            googleLoginManager.signInResult(data);
        }
        else if (type == Constants.FB_SIGN_IN){

        }
    }


    public void apiConnect(CallbackListener listener) {
        if (type == Constants.GOOGLE_SIGN_IN){
            googleLoginManager.apiConnect(listener);
        }
        else if (type == Constants.FB_SIGN_IN){

        }
    }

    public void getUser(CallbackListener<FirebaseUser> callbackListener) {
        if (type == Constants.GOOGLE_SIGN_IN){
            googleLoginManager.getUser(callbackListener);
        }
        else if (type == Constants.FB_SIGN_IN){

        }
    }
}
