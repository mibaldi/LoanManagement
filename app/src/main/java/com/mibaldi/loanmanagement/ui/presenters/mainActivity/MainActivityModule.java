package com.mibaldi.loanmanagement.ui.presenters.mainActivity;


import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.Params;
import com.mibaldi.loanmanagement.data.repositories.LoginRepository;
import com.mibaldi.loanmanagement.data.repositories.UserDataRepository;
import com.mibaldi.loanmanagement.domain.features.Auth.AuthInteractor;
import com.mibaldi.loanmanagement.domain.features.Auth.AuthInteractorImpl;
import com.mibaldi.loanmanagement.domain.features.feature1.Feature1Interactor;
import com.mibaldi.loanmanagement.domain.features.feature1.Feature1InteractorImpl;

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
