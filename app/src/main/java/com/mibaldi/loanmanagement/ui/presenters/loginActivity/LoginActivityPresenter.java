package com.mibaldi.loanmanagement.ui.presenters.loginActivity;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.loanmanagement.base.BasePresenter;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;
import com.mibaldi.loanmanagement.domain.callbacks.MyError;
import com.mibaldi.loanmanagement.domain.features.Auth.AuthInteractor;
import com.mibaldi.loanmanagement.domain.features.Auth.AuthInteractorImpl;
import com.mibaldi.loanmanagement.router.Router;
import com.mibaldi.loanmanagement.ui.views.SecondActivityView;
import com.mibaldi.loanmanagement.utils.Constants;

import javax.inject.Inject;


public class LoginActivityPresenter extends BasePresenter<SecondActivityView> {

    @Inject
    Router router;

    @Inject
    AuthInteractorImpl authInteractor;
    @Inject
    LoginActivityPresenter() {}

    public void init(final Context context) {
        this.activityContext = context;
        authInteractor.init(activityContext, new CallbackListener<FirebaseUser>() {
            @Override
            public void onSuccess(FirebaseUser result) {
                if(result != null){
                    getView().hideProgress();
                    router.goToMainActivity();
                    router.finishActivity(activityContext);
                }
            }
            @Override
            public void onError(MyError myError) {
                Toast.makeText(activityContext,"Usuario no valido",Toast.LENGTH_SHORT).show();
                //getView().showMessage("Usuario No valido");
            }
        }, Constants.GOOGLE_SIGN_IN);
    }


    public void signInResult(Intent data) {
        authInteractor.onActivityResult(data);
    }

    public void login() {
        getView().showProgress();
        authInteractor.login();
    }


    public void addAuthState() {
        authInteractor.addAuthState();
    }

    public void removeAuthState(){
        authInteractor.removeAuthState();

    }
}
