package com.mibaldi.loanmanagement.ui.presenters.mainActivity;


import com.mibaldi.loanmanagement.application.LoanManagementApplicationComponent;
import com.mibaldi.loanmanagement.di.PerActivity;
import com.mibaldi.loanmanagement.domain.features.feature1.Feature1Interactor;

import dagger.Component;
import dagger.Provides;

@PerActivity
@Component(dependencies = LoanManagementApplicationComponent.class,modules = MainActivityModule.class)
public interface MainActivityComponent {
        MainActivityPresenter presenter();
        Feature1Interactor getFeature1Interactor();
}
