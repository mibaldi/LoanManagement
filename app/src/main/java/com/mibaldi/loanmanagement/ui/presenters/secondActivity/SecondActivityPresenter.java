package com.mibaldi.loanmanagement.ui.presenters.secondActivity;

import android.content.Context;

import com.mibaldi.loanmanagement.base.BasePresenter;
import com.mibaldi.loanmanagement.router.Router;
import com.mibaldi.loanmanagement.ui.views.SecondActivityView;

import javax.inject.Inject;


public class SecondActivityPresenter extends BasePresenter<SecondActivityView> {

    @Inject
    Router router;

    @Inject
    SecondActivityPresenter() {}

    public void init(final Context context) {
        this.activityContext = context;
    }




}
