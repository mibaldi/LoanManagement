package com.mibaldi.loanmanagement.ui.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mibaldi.loanmanagement.R;
import com.mibaldi.loanmanagement.base.baseMosby.activity.BaseMVPActivity;
import com.mibaldi.loanmanagement.di.HasComponent;
import com.mibaldi.loanmanagement.ui.presenters.secondActivity.DaggerSecondActivityComponent;
import com.mibaldi.loanmanagement.ui.presenters.secondActivity.SecondActivityComponent;
import com.mibaldi.loanmanagement.ui.presenters.secondActivity.SecondActivityPresenter;
import com.mibaldi.loanmanagement.ui.views.SecondActivityView;


public class SecondActivity extends BaseMVPActivity<SecondActivityPresenter, SecondActivityView>
        implements SecondActivityView, HasComponent<SecondActivityComponent> {

    private SecondActivityComponent secondActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        presenter.init(this);
    }

    @NonNull
    @Override
    public SecondActivityPresenter createPresenter() {
        return secondActivityComponent.presenter();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public SecondActivityComponent getComponent() {
        return secondActivityComponent;
    }
    private void initializeInjector() {
        this.secondActivityComponent = DaggerSecondActivityComponent.builder().loanManagementApplicationComponent(getInjector()).build();
    }
}
