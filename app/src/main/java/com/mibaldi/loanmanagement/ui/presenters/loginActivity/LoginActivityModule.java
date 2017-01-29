package com.mibaldi.loanmanagement.ui.presenters.loginActivity;


import com.mibaldi.loanmanagement.data.repositories.LoginRepository;
import com.mibaldi.loanmanagement.domain.features.Auth.AuthInteractor;
import com.mibaldi.loanmanagement.domain.features.Auth.AuthInteractorImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginActivityModule {
    public LoginActivityModule(){}
    @Provides
    public AuthInteractor getAuthInteractor(LoginRepository loginRepository){
        return new AuthInteractorImpl(loginRepository);
    }

}
