package com.mibaldi.loanmanagement.ui.presenters.thirdActivity;


import android.content.Context;

import com.mibaldi.loanmanagement.data.repositories.LoginRepository;
import com.mibaldi.loanmanagement.di.PerActivity;
import com.mibaldi.loanmanagement.domain.features.auth.AuthInteractor;
import com.mibaldi.loanmanagement.domain.features.auth.AuthInteractorImpl;
import com.mibaldi.loanmanagement.router.thirdActivity.ThirdActivityRouter;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class ThirdActivityModule {
    public Context activityContext;
    public ThirdActivityModule(Context context){
        this.activityContext = context;
    }

    @Provides
    @PerActivity
    public ThirdActivityRouter getThirdActivityRouter(){
        return new ThirdActivityRouter(activityContext);
    }
}
