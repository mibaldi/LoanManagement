package com.mibaldi.loanmanagement.ui.presenters.debtorListActivity;

import android.content.Context;
import android.widget.Toast;

import com.mibaldi.loanmanagement.base.BasePresenter;
import com.mibaldi.loanmanagement.data.mappers.Mappers;
import com.mibaldi.loanmanagement.data.models.Debtor;
import com.mibaldi.loanmanagement.data.models.Money;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;
import com.mibaldi.loanmanagement.domain.callbacks.MyError;
import com.mibaldi.loanmanagement.domain.features.auth.AuthInteractor;
import com.mibaldi.loanmanagement.domain.features.createDebtor.DebtorInteractor;
import com.mibaldi.loanmanagement.domain.features.debtorList.DebtorListInteractor;
import com.mibaldi.loanmanagement.router.Router;
import com.mibaldi.loanmanagement.ui.adapters.DebtorListAdapter;
import com.mibaldi.loanmanagement.ui.adapters.DebtorListFirebaseAdapter;
import com.mibaldi.loanmanagement.ui.views.DebtorListActivityView;
import com.mibaldi.loanmanagement.ui.views.DebtorModifyActivityView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.mibaldi.loanmanagement.data.validators.DebtorValidator.validateCreator;


public class DebtorListActivityPresenter extends BasePresenter<DebtorListActivityView> {

    @Inject
    Router router;

    @Inject
    DebtorListInteractor debtorListInteractor;

    @Inject
    DebtorListActivityPresenter() {}

    public void init(final Context context) {
        this.activityContext = context;
        refreshList();
    }

    public void refreshList() {
        getView().initializeAdapter(null, new DebtorListFirebaseAdapter.OnItemClickListener() {
            @Override
            public void openDebtorDetail(Debtor debtor) {
                debtorListInteractor.setCurrentDebtor(debtor);
                router.goToDebtorModifyActivity(true);
            }
        });
       /* debtorListInteractor.getDebtorList(new CallbackListener<List<Debtor>>() {
            @Override
            public void onSuccess(List<Debtor> result) {

            }

            @Override
            public void onError(MyError myError) {

            }
        });*/
    }


}
