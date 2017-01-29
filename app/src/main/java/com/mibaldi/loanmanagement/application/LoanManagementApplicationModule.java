package com.mibaldi.loanmanagement.application;

import android.content.Context;
import android.util.Log;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;
import com.birbit.android.jobqueue.log.CustomLogger;
import com.mibaldi.loanmanagement.data.Managers.GoogleLoginManager;
import com.mibaldi.loanmanagement.data.repositories.LoginRepository;
import com.mibaldi.loanmanagement.data.repositories.UserDataRepository;
import com.mibaldi.loanmanagement.router.Router;


import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LoanManagementApplicationModule {
    private final Context context;
    public LoanManagementApplicationModule(Context context){
        this.context = context;
    }

    @Named("ApplicationContext")
    @Provides
    @Singleton
    Context providedApplicationContext(){
        return this.context;
    }

    @Provides
    @Singleton
    Router providedRouter(){
        return new Router(this.context);
    }

    @Provides
    @Singleton
    LoginRepository providedLoginRepository(){
        GoogleLoginManager googleLoginManager = new GoogleLoginManager();
        return new LoginRepository(googleLoginManager);
    }


}
