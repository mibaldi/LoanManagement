package com.mibaldi.loanmanagement.ui.presenters.debtorModifyActivity;



import com.mibaldi.loanmanagement.application.LoanManagementApplicationComponent;
import com.mibaldi.loanmanagement.di.PerActivity;
import com.mibaldi.loanmanagement.domain.features.auth.AuthInteractor;
import com.mibaldi.loanmanagement.domain.features.createDebtor.DebtorInteractor;
import com.mibaldi.loanmanagement.domain.features.user.UserInteractor;

import dagger.Component;

@PerActivity
@Component(dependencies = LoanManagementApplicationComponent.class,modules = DebtorModifyActivityModule.class)
public interface DebtorModifyActivityComponent {

        DebtorModifyActivityPresenter presenter();
        AuthInteractor getAuthInteractor();
        DebtorInteractor getDebtorInteractor();

}
