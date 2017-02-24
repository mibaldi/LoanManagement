package com.mibaldi.loanmanagement.ui.presenters.thirdActivity;


import com.mibaldi.loanmanagement.application.LoanManagementApplicationComponent;
import com.mibaldi.loanmanagement.di.PerActivity;
import com.mibaldi.loanmanagement.domain.features.createDebtor.DebtorInteractor;
import com.mibaldi.loanmanagement.router.thirdActivity.ThirdActivityRouter;
import com.mibaldi.loanmanagement.ui.fragments.FirstThirdFragment;
import com.mibaldi.loanmanagement.ui.fragments.SecondThirdFragment;
import com.mibaldi.loanmanagement.ui.presenters.thirdActivity.firstThirdFragment.FirstThirdFragmentModule;
import com.mibaldi.loanmanagement.ui.presenters.thirdActivity.firstThirdFragment.FirstThirdFragmentPresenter;
import com.mibaldi.loanmanagement.ui.presenters.thirdActivity.secondThirdFragment.SecondThirdFragmentModule;
import com.mibaldi.loanmanagement.ui.presenters.thirdActivity.secondThirdFragment.SecondThirdFragmentPresenter;

import dagger.Component;

@PerActivity
@Component(dependencies = LoanManagementApplicationComponent.class,modules = {ThirdActivityModule.class, FirstThirdFragmentModule.class, SecondThirdFragmentModule.class})
public interface ThirdActivityComponent {
        void inject (FirstThirdFragment firstThirdFragment);
        void inject (SecondThirdFragment secondThirdFragment);


        ThirdActivityPresenter presenter();
        FirstThirdFragmentPresenter firstThirdFragmentPresenter();
        SecondThirdFragmentPresenter secondThirdFragmentPresenter();


        DebtorInteractor getDebtorInteractor();

        ThirdActivityRouter router();
}
