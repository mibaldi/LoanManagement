package com.mibaldi.loanmanagement.ui.presenters.debtorModifyActivity;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.common.api.BooleanResult;
import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.loanmanagement.base.BasePresenter;
import com.mibaldi.loanmanagement.data.mappers.Mappers;
import com.mibaldi.loanmanagement.data.models.Debtor;
import com.mibaldi.loanmanagement.data.repositories.DebtorDataRepository;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;
import com.mibaldi.loanmanagement.domain.callbacks.MyError;
import com.mibaldi.loanmanagement.domain.features.auth.AuthInteractor;
import com.mibaldi.loanmanagement.domain.features.createDebtor.DebtorInteractor;
import com.mibaldi.loanmanagement.domain.features.user.UserInteractorImpl;
import com.mibaldi.loanmanagement.router.Router;
import com.mibaldi.loanmanagement.ui.views.DebtorModifyActivityView;
import com.mibaldi.loanmanagement.utils.Constants;

import javax.inject.Inject;

import static com.mibaldi.loanmanagement.data.validators.DebtorValidator.validateCreator;


public class DebtorModifyActivityPresenter extends BasePresenter<DebtorModifyActivityView> {

    boolean update = false;
    Debtor currentDebtor = null;


    @Inject
    Router router;

    @Inject
    AuthInteractor authInteractor;

    @Inject
    DebtorInteractor debtorInteractor;

    @Inject
    DebtorModifyActivityPresenter() {}

    public void init(final Context context, boolean update) {
        this.activityContext = context;
        this.update = update;

        if (this.update) {
            debtorInteractor.getCurrentDebtor(new CallbackListener<Debtor>() {

                @Override
                public void onSuccess(Debtor result) {
                    currentDebtor = result;
                    getView().setupView(result.getName(),result.getEmail());
                }

                @Override
                public void onError(MyError myError) {

                }
            });
        }

    }


    public void createDebtor(String debtorName, String debtorEmail) {
        Debtor debtor = new Debtor(debtorName, debtorEmail);

        if (validateCreator(debtor)){
            if (update){
                debtorInteractor.modifyDebtor(currentDebtor.getId(),Mappers.DebtorToMap(debtor), new CallbackListener<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {
                        Toast.makeText(activityContext,result.toString(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(MyError myError) {
                        Toast.makeText(activityContext,"error",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else {
                debtorInteractor.createDebtor(Mappers.DebtorToMap(debtor), new CallbackListener<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(activityContext,result,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(MyError myError) {
                        Toast.makeText(activityContext,"error",Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }
    }


}
