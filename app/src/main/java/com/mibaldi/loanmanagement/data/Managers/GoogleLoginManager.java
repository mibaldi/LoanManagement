package com.mibaldi.loanmanagement.data.managers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.mibaldi.loanmanagement.R;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;
import com.mibaldi.loanmanagement.domain.callbacks.ErrorConstants;
import com.mibaldi.loanmanagement.domain.callbacks.MyError;
import com.mibaldi.loanmanagement.utils.Constants;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GoogleLoginManager implements LoginClientManager {
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private CallbackListener<FirebaseUser> connectionListener;
    private Context context;
    private GoogleApiClient.Builder mGoogleBuilder;

    @Inject
    public GoogleLoginManager(){
        mAuth = FirebaseAuth.getInstance();
    }

    private void activateSignInOptions(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        if (mAuthListener == null){
            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    connectionListener.onSuccess(user);
                }
            };
        }


        mGoogleApiClient = mGoogleBuilder.enableAutoManage((FragmentActivity) context, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                MyError myError = new MyError();
                myError.setError(ErrorConstants.ERROR_400);
                myError.setDescription(connectionResult.getErrorMessage());
                connectionListener.onError(myError);
            }
        }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
    }

    @Override
    public void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        ((FragmentActivity)context).startActivityForResult(signInIntent, Constants.GOOGLE_SIGN_IN);
    }

    @Override
    public void signOut(final CallbackListener dataResultListener) {
        mAuth.signOut();
        if (mGoogleApiClient.isConnected()){
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallbacks<Status>() {
                @Override
                public void onSuccess(@NonNull Status status) {
                    dataResultListener.onSuccess(null);
                }

                @Override
                public void onFailure(@NonNull Status status) {
                    MyError myError = new MyError();
                    myError.setError(ErrorConstants.ERROR_400);
                    myError.setDescription(status.getStatusMessage());
                    dataResultListener.onError(myError);
                }
            });
        }else {
            mGoogleApiClient.connect();
            MyError myError = new MyError();
            myError.setError(ErrorConstants.ERROR_400);
            myError.setDescription("conectando");
            dataResultListener.onError(myError);
        }
    }

    @Override
    public void init(Context context, CallbackListener<FirebaseUser> connectionListener) {
        this.connectionListener = connectionListener;
        this.context = context;
        mGoogleBuilder = new GoogleApiClient.Builder(context);
        activateSignInOptions();
    }

    @Override
    public void addAuthState() {
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void removeAuthState() {
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void signInResult(Intent data) {
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            firebaseAuthWithGoogle(account);
        } else {
            MyError myError = new MyError();
            myError.setError(ErrorConstants.ERROR_400);
            myError.setDescription(result.getStatus().getStatusMessage());
            connectionListener.onError(myError);
        }
    }

    @Override
    public void getUser(CallbackListener<FirebaseUser> callbackListener) {
        if (mAuth.getCurrentUser() != null) {
            callbackListener.onSuccess(mAuth.getCurrentUser());
        }else {
            MyError myError = new MyError();
            myError.setError(ErrorConstants.ERROR_400);
            myError.setDescription("Without user");
            callbackListener.onError(myError);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //connectionListener.onSuccess(task.getResult().getUser());
                    }
                });
    }

    @Override
    public void apiConnect(CallbackListener dataResultListener) {
        if(mGoogleApiClient != null && !(mGoogleApiClient.isConnecting()) && !(mGoogleApiClient.isConnected())){
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void apiDisconnect(CallbackListener dataResultListener) {
        if (mGoogleApiClient.isConnecting() || mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
}
