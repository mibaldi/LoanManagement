package com.mibaldi.loanmanagement.domain.features.feature1;


import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.mibaldi.loanmanagement.data.repositories.UserDataRepository;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;
import com.mibaldi.loanmanagement.domain.callbacks.MyError;

import javax.inject.Inject;

public class FeatureJob extends Job {
    public static final int PRIORITY = 1;
    public final CallbackListener<String> listener;
    public UserDataRepository userDataRepository;
    public String name = "";


    public FeatureJob(UserDataRepository userDataRepository,CallbackListener<String> listener) {
        super(new Params(PRIORITY).requireNetwork());
        this.userDataRepository = userDataRepository;
        this.listener = listener;
    }


    @Override
    public void onAdded() {
        // Job has been saved to disk.
        // This is a good place to dispatch a UI event to indicate the job will eventually run.
        // In this example, it would be good to update the UI with the newly posted tweet.

    }

    @Override
    public void onRun() throws Throwable {
        // Job logic goes here. In this example, the network call to post to Twitter is done here.
        // All work done here should be synchronous, a job is removed from the queue once
        // onRun() finishes.

        userDataRepository.getUser(new CallbackListener<String>() {
            @Override
            public void onSuccess(final String result) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    public void run() {
                        listener.onSuccess(result);
                    }
                });
            }

            @Override
            public void onError(final MyError myError) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    public void run() {

                        listener.onError(myError);
                    }
                });
            }
        });




    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {
        // Job has exceeded retry attempts or shouldReRunOnThrowable() has decided to cancel.
    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount, int maxRunCount) {
        // An error occurred in onRun.
        // Return value determines whether this job should retry or cancel. You can further
        // specify a backoff strategy or change the job's priority. You can also apply the
        // delay to the whole group to preserve jobs' running order.
        return RetryConstraint.createExponentialBackoff(runCount, 1000);
    }
}
