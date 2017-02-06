package com.mibaldi.loanmanagement.ui.presenters.debtorListActivity;


import com.mibaldi.loanmanagement.data.repositories.DebtorDataRepository;
import com.mibaldi.loanmanagement.data.repositories.LoginRepository;
import com.mibaldi.loanmanagement.di.PerActivity;
import com.mibaldi.loanmanagement.domain.features.auth.AuthInteractor;
import com.mibaldi.loanmanagement.domain.features.auth.AuthInteractorImpl;
import com.mibaldi.loanmanagement.domain.features.createDebtor.DebtorInteractor;
import com.mibaldi.loanmanagement.domain.features.createDebtor.DebtorInteractorImpl;
import com.mibaldi.loanmanagement.domain.features.debtorList.DebtorListInteractor;
import com.mibaldi.loanmanagement.domain.features.debtorList.DebtorListInteractorImpl;
import com.mibaldi.loanmanagement.ui.adapters.DebtorListAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class DebtorListActivityModule {
    public DebtorListActivityModule(){}
    @Provides
    public AuthInteractor getAuthInteractor(LoginRepository loginRepository){
        return new AuthInteractorImpl(loginRepository);
    }
    @Provides
    public DebtorListInteractor getDebtorListInteractor(DebtorDataRepository debtorDataRepository){
        return new DebtorListInteractorImpl(debtorDataRepository);
    }

    @Provides
    @PerActivity
    DebtorListAdapter provideDebtorListAdapter(){return new DebtorListAdapter();}
}
