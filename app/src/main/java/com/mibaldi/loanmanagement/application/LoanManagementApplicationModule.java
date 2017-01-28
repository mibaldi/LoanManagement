package com.mibaldi.loanmanagement.application;

import android.content.Context;
import android.util.Log;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;
import com.birbit.android.jobqueue.log.CustomLogger;
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
    JobManager providedJobManager(){
        Configuration.Builder builder = new Configuration.Builder(context)
                .customLogger(new CustomLogger() {
                    private static final String TAG = "JOBS";
                    @Override
                    public boolean isDebugEnabled() {
                        return true;
                    }

                    @Override
                    public void d(String text, Object... args) {
                        Log.d(TAG, String.format(text, args));
                    }

                    @Override
                    public void e(Throwable t, String text, Object... args) {
                        Log.e(TAG, String.format(text, args), t);
                    }

                    @Override
                    public void e(String text, Object... args) {
                        Log.e(TAG, String.format(text, args));
                    }

                    @Override
                    public void v(String text, Object... args) {

                    }
                })
                .minConsumerCount(1)//always keep at least one consumer alive
                .maxConsumerCount(3)//up to 3 consumers at a time
                .loadFactor(3)//3 jobs per consumer
                .consumerKeepAlive(120);//wait 2 minute
        JobManager jobManager = new JobManager(builder.build());
        return jobManager;
    }
    @Provides
    @Singleton
    public UserDataRepository getUserDataRepository() {
        return new UserDataRepository();
    }

}
