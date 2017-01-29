package com.mibaldi.loanmanagement.ui.presenters.loginActivity;



import com.mibaldi.loanmanagement.application.LoanManagementApplicationComponent;
import com.mibaldi.loanmanagement.di.PerActivity;
import com.mibaldi.loanmanagement.domain.features.auth.AuthInteractor;
import com.mibaldi.loanmanagement.domain.features.user.UserInteractor;

import dagger.Component;

@PerActivity
@Component(dependencies = LoanManagementApplicationComponent.class,modules = LoginActivityModule.class)
public interface LoginActivityComponent {
        LoginActivityPresenter presenter();
        AuthInteractor getAuthInteractor();
        UserInteractor getUserInteractor();

}
