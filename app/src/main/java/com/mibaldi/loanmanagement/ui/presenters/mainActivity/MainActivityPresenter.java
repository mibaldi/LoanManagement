package com.mibaldi.loanmanagement.ui.presenters.mainActivity;

import android.content.Context;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mibaldi.loanmanagement.base.BasePresenter;
import com.mibaldi.loanmanagement.data.models.Debtor;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;
import com.mibaldi.loanmanagement.domain.callbacks.MyError;
import com.mibaldi.loanmanagement.domain.features.auth.AuthInteractor;
import com.mibaldi.loanmanagement.router.Router;
import com.mibaldi.loanmanagement.ui.views.MainActivityView;
import com.mibaldi.loanmanagement.utils.Constants;

import javax.inject.Inject;


public class MainActivityPresenter extends BasePresenter<MainActivityView> {

    @Inject
    Router router;

    @Inject
    AuthInteractor authInteractor;

    @Inject
    MainActivityPresenter() {}

    public void init(final Context context) {
        this.activityContext = context;
        authInteractor.init(activityContext, new CallbackListener<FirebaseUser>() {
            @Override
            public void onSuccess(FirebaseUser result) {
            }

            @Override
            public void onError(MyError myError) {
                getView().showMessage(myError.getDescription());
            }
        }, Constants.GOOGLE_SIGN_IN);
        getUser();
    }
    public void getUser(){
        authInteractor.getUser(new CallbackListener<FirebaseUser>() {
            @Override
            public void onSuccess(FirebaseUser result) {

                getView().showLogout();
                getView().showUserInfo(result.getDisplayName(),result.getPhotoUrl().toString());
            }

            @Override
            public void onError(MyError myError) {
                getView().showMessage(myError.getDescription());
            }
        });
    }

    public void logout() {

        authInteractor.logout(new CallbackListener<FirebaseUser>() {
            @Override
            public void onSuccess(FirebaseUser result) {
                authInteractor.removeAuthState();
                router.goToLoginActivity();
                router.finishActivity(activityContext);
            }

            @Override
            public void onError(MyError myError) {

            }
        });
    }

    public void createDebtor() {
        router.goToDebtorModifyActivity(false);
    }

    public void goToDebtorList() {
        router.goToDebtorListActivity();
    }
}
