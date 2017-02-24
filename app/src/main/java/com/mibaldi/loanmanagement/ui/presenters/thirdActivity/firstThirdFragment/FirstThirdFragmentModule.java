package com.mibaldi.loanmanagement.ui.presenters.thirdActivity.firstThirdFragment;

import com.mibaldi.loanmanagement.data.repositories.DebtorDataRepository;
import com.mibaldi.loanmanagement.di.PerActivity;
import com.mibaldi.loanmanagement.domain.features.createDebtor.DebtorInteractor;
import com.mibaldi.loanmanagement.domain.features.createDebtor.DebtorInteractorImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class FirstThirdFragmentModule {
    public FirstThirdFragmentModule() {
    }

    @PerActivity
    @Provides
    public DebtorInteractor getDebtorInteractor(DebtorDataRepository debtorDataRepository){
        return new DebtorInteractorImpl(debtorDataRepository);
    }

}