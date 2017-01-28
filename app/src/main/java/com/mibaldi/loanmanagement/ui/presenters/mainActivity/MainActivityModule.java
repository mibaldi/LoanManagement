package com.mibaldi.loanmanagement.ui.presenters.mainActivity;


import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.Params;
import com.mibaldi.loanmanagement.data.repositories.UserDataRepository;
import com.mibaldi.loanmanagement.domain.features.feature1.Feature1Interactor;
import com.mibaldi.loanmanagement.domain.features.feature1.Feature1InteractorImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    public MainActivityModule(){}

    @Provides
    public Feature1Interactor getFeature1Interactor(UserDataRepository userDataRepository, JobManager jobManager){
        return new Feature1InteractorImpl(userDataRepository,jobManager);
    }
}
