package com.mibaldi.loanmanagement.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;
import com.birbit.android.jobqueue.log.CustomLogger;
import com.birbit.android.jobqueue.scheduling.FrameworkJobSchedulerService;
import com.birbit.android.jobqueue.scheduling.GcmJobSchedulerService;

/**
 * Application
 */
public class LoanManagementApplication extends Application {
    private LoanManagementApplicationComponent loanManagementApplicationComponent;
    private JobManager jobManager;


    @Override
    public void onCreate() {
        super.onCreate();
        loanManagementApplicationComponent = DaggerLoanManagementApplicationComponent.builder().loanManagementApplicationModule(new LoanManagementApplicationModule(this)).build();
    }

    public LoanManagementApplicationComponent getInjector() {
        return loanManagementApplicationComponent;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

}
