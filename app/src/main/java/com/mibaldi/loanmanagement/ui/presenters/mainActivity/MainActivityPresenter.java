package com.mibaldi.loanmanagement.ui.presenters.mainActivity;

import android.app.Activity;
import android.content.Context;

import com.birbit.android.jobqueue.JobManager;
import com.mibaldi.loanmanagement.base.BasePresenter;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;
import com.mibaldi.loanmanagement.domain.callbacks.MyError;
import com.mibaldi.loanmanagement.domain.features.feature1.Feature1Interactor;
import com.mibaldi.loanmanagement.router.Router;
import com.mibaldi.loanmanagement.ui.views.MainActivityView;

import javax.inject.Inject;


public class MainActivityPresenter extends BasePresenter<MainActivityView> {

    @Inject
    Router router;

    @Inject
    Feature1Interactor feature1Interactor;

    @Inject
    MainActivityPresenter() {}

    public void init(final Context context) {
        this.activityContext = context;

    }
    public void getUser(){
        feature1Interactor.getUser(new CallbackListener<String>() {
            @Override
            public void onSuccess(String result) {
                getView().showMessage(result);
            }

            @Override
            public void onError(MyError myError) {
                getView().showMessage(myError.getDescription());
            }
        });
    }

    public void goSecondActivity() {
        router.goToSecondActivity();
    }
}
