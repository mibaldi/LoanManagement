package com.mibaldi.loanmanagement.ui.presenters.secondActivity;



import com.mibaldi.loanmanagement.application.LoanManagementApplicationComponent;
import com.mibaldi.loanmanagement.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = LoanManagementApplicationComponent.class,modules = SecondActivityModule.class)
public interface SecondActivityComponent {
        SecondActivityPresenter presenter();
}
