package com.mibaldi.loanmanagement.ui.presenters.loginActivity;


import com.mibaldi.loanmanagement.data.repositories.LoginRepository;
import com.mibaldi.loanmanagement.data.repositories.UserDataRepository;
import com.mibaldi.loanmanagement.domain.features.auth.AuthInteractor;
import com.mibaldi.loanmanagement.domain.features.auth.AuthInteractorImpl;
import com.mibaldi.loanmanagement.domain.features.user.UserInteractor;
import com.mibaldi.loanmanagement.domain.features.user.UserInteractorImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginActivityModule {
    public LoginActivityModule(){}
    @Provides
    public AuthInteractor getAuthInteractor(LoginRepository loginRepository){
        return new AuthInteractorImpl(loginRepository);
    }
    @Provides
    public UserInteractor getUserInteractor(UserDataRepository userDataRepository){
        return new UserInteractorImpl(userDataRepository);
    }
}
