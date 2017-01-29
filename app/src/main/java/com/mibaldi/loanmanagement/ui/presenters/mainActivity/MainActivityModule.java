package com.mibaldi.loanmanagement.ui.presenters.mainActivity;


import com.mibaldi.loanmanagement.data.repositories.LoginRepository;
import com.mibaldi.loanmanagement.domain.features.auth.AuthInteractor;
import com.mibaldi.loanmanagement.domain.features.auth.AuthInteractorImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    public MainActivityModule(){}

    @Provides
    public AuthInteractor getAuthInteractor(LoginRepository loginRepository){
        return new AuthInteractorImpl(loginRepository);
    }
}
