package com.mibaldi.loanmanagement.domain.features.Auth;

import android.content.Context;
import android.content.Intent;
import android.telecom.Call;

import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.loanmanagement.di.PerActivity;
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
