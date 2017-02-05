package com.mibaldi.loanmanagement.data.managers;

import android.content.Context;
import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;


public interface LoginClientManager {

    void signIn();
    void signOut(CallbackListener<FirebaseUser> dataResultListener);
    void init(Context context, CallbackListener<FirebaseUser> connectionListener);
    void addAuthState();
    void removeAuthState();
    void signInResult(Intent data);
    void apiConnect(CallbackListener<FirebaseUser> dataResultListener);
    void apiDisconnect(CallbackListener<FirebaseUser> dataResultListener);

    void getUser(CallbackListener<FirebaseUser> callbackListener);
}
