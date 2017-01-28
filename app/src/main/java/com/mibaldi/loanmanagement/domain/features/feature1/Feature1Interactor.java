package com.mibaldi.loanmanagement.domain.features.feature1;


import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;


public interface Feature1Interactor  {
  void getUser(CallbackListener<String> listener);
}
