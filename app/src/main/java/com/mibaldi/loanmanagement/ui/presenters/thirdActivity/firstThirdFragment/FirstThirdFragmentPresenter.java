package com.mibaldi.loanmanagement.ui.presenters.thirdActivity.firstThirdFragment;

import android.content.Context;
import android.widget.Toast;

import com.mibaldi.loanmanagement.base.BasePresenter;
import com.mibaldi.loanmanagement.data.models.Debtor;
import com.mibaldi.loanmanagement.di.PerActivity;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;
import com.mibaldi.loanmanagement.domain.callbacks.MyError;
import com.mibaldi.loanmanagement.domain.features.createDebtor.DebtorInteractor;
import com.mibaldi.loanmanagement.router.Router;
import com.mibaldi.loanmanagement.router.thirdActivity.ThirdActivityRouter;
import com.mibaldi.loanmanagement.ui.views.thirdActivity.FirstThirdFragmentView;

import javax.inject.Inject;

@PerActivity
public class FirstThirdFragmentPresenter extends BasePresenter<FirstThirdFragmentView> {

    @Inject
    Router router;

    @Inject
    ThirdActivityRouter activityRouter;

    private Context context;


    @Inject
    DebtorInteractor debtorInteractor;

    @Inject
    public FirstThirdFragmentPresenter() {
    }

    public void init(final Context context){
        this.context = context;

        debtorInteractor.getCurrentDebtor(new CallbackListener<Debtor>() {
            @Override
            public void onSuccess(Debtor result) {
                Toast.makeText(context,"aa",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(MyError myError) {
                Toast.makeText(context,"error",Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void nextStep() {
        activityRouter.addSecondFragment();
    }
}