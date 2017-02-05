package com.mibaldi.loanmanagement.application;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;


import com.mibaldi.loanmanagement.data.repositories.DebtorDataRepository;
import com.mibaldi.loanmanagement.data.repositories.LoginRepository;
import com.mibaldi.loanmanagement.data.repositories.UserDataRepository;
import com.mibaldi.loanmanagement.router.Router;


import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = LoanManagementApplicationModule.class)
public interface LoanManagementApplicationComponent {
    @Named("ApplicationContext")
    Context context();
    void inject(AppCompatActivity baseActivity);
    Router getRouter();
    LoginRepository getLoginRepository();
    UserDataRepository getUserDataRepository();
    DebtorDataRepository getDebtorDataRepository();
}
