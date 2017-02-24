package com.mibaldi.loanmanagement.ui.presenters.thirdActivity;

import android.content.Context;

import com.mibaldi.loanmanagement.base.BasePresenter;
import com.mibaldi.loanmanagement.base.baseMosby.fragment.BaseMVPFragment;
import com.mibaldi.loanmanagement.router.Router;
import com.mibaldi.loanmanagement.router.thirdActivity.ThirdActivityRouter;
import com.mibaldi.loanmanagement.ui.views.thirdActivity.ThirdActivityView;

import javax.inject.Inject;


public class ThirdActivityPresenter extends BasePresenter<ThirdActivityView> {

    @Inject
    Router router;

    @Inject
    ThirdActivityRouter thirdActivityRouter;


    @Inject
    ThirdActivityPresenter() {}

    public void init(final Context context) {
        this.activityContext = context;
    }

    public void addFragment() {

        thirdActivityRouter.addFirstFragment();
    }
}
