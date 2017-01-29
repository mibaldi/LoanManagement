package com.mibaldi.loanmanagement.domain.features.auth;

import android.content.Context;
import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;


public interface AuthInteractor {
    void init(Context context, CallbackListener<FirebaseUser> listener, int type);
    void onActivityResult(Intent data);
    void login();
    void logout(final CallbackListener<FirebaseUser> listener);
    void addAuthState();
    void removeAuthState();
    void apiConect(CallbackListener<FirebaseUser>listener);
}
