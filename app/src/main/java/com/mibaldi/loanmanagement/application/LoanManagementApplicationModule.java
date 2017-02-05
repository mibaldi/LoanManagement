package com.mibaldi.loanmanagement.application;

import android.content.Context;

import com.mibaldi.loanmanagement.data.managers.GoogleLoginManager;
import com.mibaldi.loanmanagement.data.repositories.DebtorDataRepository;
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
    @Provides
    @Singleton
    UserDataRepository providedUserDataRepository(){
        return new UserDataRepository();
    }

    @Provides
    @Singleton
    DebtorDataRepository providedDebtorDataRepository(){
        return new DebtorDataRepository();
    }


}
