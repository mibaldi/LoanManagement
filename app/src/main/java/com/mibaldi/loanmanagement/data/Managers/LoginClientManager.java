package com.mibaldi.loanmanagement.data.Managers;

import android.content.Context;
import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;


import javax.inject.Singleton;


public interface LoginClientManager {

    void signIn();
    void signOut(CallbackListener<FirebaseUser> dataResultListener);
    void init(Context context, CallbackListener<FirebaseUser> connectionListener);
    void addAuthState();
    void removeAuthState();
    void signInResult(Intent data);
    void apiConnect(CallbackListener<FirebaseUser> dataResultListener);
    void apiDisconnect(CallbackListener<FirebaseUser> dataResultListener);
}
