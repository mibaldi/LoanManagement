package com.mibaldi.loanmanagement.ui.presenters.debtorListActivity;



import com.mibaldi.loanmanagement.application.LoanManagementApplicationComponent;
import com.mibaldi.loanmanagement.di.PerActivity;
import com.mibaldi.loanmanagement.domain.features.auth.AuthInteractor;
import com.mibaldi.loanmanagement.domain.features.createDebtor.DebtorInteractor;
import com.mibaldi.loanmanagement.domain.features.debtorList.DebtorListInteractor;
import com.mibaldi.loanmanagement.ui.adapters.DebtorListAdapter;

import dagger.Component;

@PerActivity
@Component(dependencies = LoanManagementApplicationComponent.class,modules = DebtorListActivityModule.class)
public interface DebtorListActivityComponent {
        DebtorListActivityPresenter presenter();
        AuthInteractor getAuthInteractor();
        DebtorListInteractor getDebtorListInteractor();
        DebtorListAdapter getDebtorListAdapter();

}
