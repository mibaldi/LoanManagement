package com.mibaldi.loanmanagement.ui.presenters.mainActivity;


import com.mibaldi.loanmanagement.application.LoanManagementApplicationComponent;
import com.mibaldi.loanmanagement.di.PerActivity;
import com.mibaldi.loanmanagement.domain.features.auth.AuthInteractor;

import dagger.Component;

@PerActivity
@Component(dependencies = LoanManagementApplicationComponent.class,modules = MainActivityModule.class)
public interface MainActivityComponent {
        MainActivityPresenter presenter();
        AuthInteractor getAuthInteractor();
}
