package com.mibaldi.loanmanagement.ui.presenters.loginActivity;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.loanmanagement.base.BasePresenter;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;
import com.mibaldi.loanmanagement.domain.callbacks.MyError;
import com.mibaldi.loanmanagement.domain.features.auth.AuthInteractor;
import com.mibaldi.loanmanagement.domain.features.user.UserInteractorImpl;
import com.mibaldi.loanmanagement.router.Router;
import com.mibaldi.loanmanagement.ui.views.LoginActivityView;
import com.mibaldi.loanmanagement.utils.Constants;

import javax.inject.Inject;


public class LoginActivityPresenter extends BasePresenter<LoginActivityView> {

    @Inject
    Router router;

    @Inject
    AuthInteractor authInteractor;

    @Inject
    UserInteractorImpl userInteractor;

    @Inject
    LoginActivityPresenter() {}

    public void init(final Context context) {
        this.activityContext = context;
        getView().showProgress();
        authInteractor.init(activityContext, new CallbackListener<FirebaseUser>() {
            @Override
            public void onSuccess(FirebaseUser result) {
                if(result != null){
                    userInteractor.createUser(result,new CallbackListener<Boolean>() {
                        @Override
                        public void onSuccess(Boolean result) {
                            if (result){
                                getView().hideProgress();
                                router.goToMainActivity();
                                router.finishActivity(activityContext);
                            }else {
                                getView().hideProgress();
                                Toast.makeText(activityContext,"Usuario no valido",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(MyError myError) {
                            getView().hideProgress();
                            Toast.makeText(activityContext,"Usuario no valido",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
            @Override
            public void onError(MyError myError) {
                getView().hideProgress();
                Toast.makeText(activityContext,"Usuario no valido",Toast.LENGTH_SHORT).show();
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
