package com.mibaldi.loanmanagement.base;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * BasePresenter with Mosby
 */
public class BasePresenter<T extends MvpView> extends MvpNullObjectBasePresenter<T> {
    public Context activityContext;
}
