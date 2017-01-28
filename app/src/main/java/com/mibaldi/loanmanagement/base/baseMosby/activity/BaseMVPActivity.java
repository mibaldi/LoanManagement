package com.mibaldi.loanmanagement.base.baseMosby.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpDelegate;
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpDelegateCallback;
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpDelegateImpl;
import com.mibaldi.loanmanagement.application.LoanManagementApplication;
import com.mibaldi.loanmanagement.application.LoanManagementApplicationComponent;
import com.mibaldi.loanmanagement.base.BasePresenter;
import com.mibaldi.loanmanagement.base.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import icepick.Icepick;

/**
 * BaseActivity with mosby for all app activities
 */
public abstract class BaseMVPActivity<P extends BasePresenter<V>, V extends BaseView> extends MvpActivity<V, P> implements BaseView {

    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getInjector().inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
        bind = ButterKnife.bind(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    public static Intent getCallingIntent(Context context,Class activityClass) {
        return new Intent(context, activityClass);
    }

    protected LoanManagementApplicationComponent getInjector(){
        return ((LoanManagementApplication) getApplication()).getInjector();
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
