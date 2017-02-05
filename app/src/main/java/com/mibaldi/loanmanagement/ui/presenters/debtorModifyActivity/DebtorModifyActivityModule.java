package com.mibaldi.loanmanagement.ui.presenters.debtorModifyActivity;


import com.mibaldi.loanmanagement.data.repositories.DebtorDataRepository;
import com.mibaldi.loanmanagement.data.repositories.LoginRepository;
import com.mibaldi.loanmanagement.data.repositories.UserDataRepository;
import com.mibaldi.loanmanagement.domain.features.auth.AuthInteractor;
import com.mibaldi.loanmanagement.domain.features.auth.AuthInteractorImpl;
import com.mibaldi.loanmanagement.domain.features.createDebtor.DebtorInteractor;
import com.mibaldi.loanmanagement.domain.features.createDebtor.DebtorInteractorImpl;
import com.mibaldi.loanmanagement.domain.features.user.UserInteractor;
import com.mibaldi.loanmanagement.domain.features.user.UserInteractorImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class DebtorModifyActivityModule {
    public DebtorModifyActivityModule(){}
    @Provides
    public AuthInteractor getAuthInteractor(LoginRepository loginRepository){
        return new AuthInteractorImpl(loginRepository);
    }
    @Provides
    public DebtorInteractor getDebtorInteractor(DebtorDataRepository debtorDataRepository){
        return new DebtorInteractorImpl(debtorDataRepository);
    }
}
