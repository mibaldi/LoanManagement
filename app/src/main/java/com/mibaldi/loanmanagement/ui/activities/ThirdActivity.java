package com.mibaldi.loanmanagement.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.mibaldi.loanmanagement.R;
import com.mibaldi.loanmanagement.base.baseMosby.activity.BaseMVPActivity;
import com.mibaldi.loanmanagement.base.baseMosby.fragment.BaseMVPFragment;
import com.mibaldi.loanmanagement.di.HasComponent;
import com.mibaldi.loanmanagement.ui.fragments.FirstThirdFragment;
import com.mibaldi.loanmanagement.ui.presenters.thirdActivity.DaggerThirdActivityComponent;
import com.mibaldi.loanmanagement.ui.presenters.thirdActivity.ThirdActivityComponent;
import com.mibaldi.loanmanagement.ui.presenters.thirdActivity.ThirdActivityModule;
import com.mibaldi.loanmanagement.ui.presenters.thirdActivity.ThirdActivityPresenter;
import com.mibaldi.loanmanagement.ui.views.thirdActivity.ThirdActivityView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ThirdActivity extends BaseMVPActivity<ThirdActivityPresenter,ThirdActivityView> implements ThirdActivityView, HasComponent<ThirdActivityComponent> {
    private ThirdActivityComponent thirdActivityComponent;
    private Unbinder unbind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        unbind = ButterKnife.bind(this);
        if(savedInstanceState == null)
            this.initializeActivity();
        presenter.init(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbind.unbind();
    }


    @NonNull
    @Override
    public ThirdActivityPresenter createPresenter() {
        return thirdActivityComponent.presenter();
    }


    private void initializeActivity() {
        selectFragment();
    }

    private void initializeInjector() {
        this.thirdActivityComponent = DaggerThirdActivityComponent.builder()
                .loanManagementApplicationComponent(getInjector())
                .thirdActivityModule(new ThirdActivityModule(this))
                .build();
    }

    public ThirdActivityComponent getComponent(){
        return thirdActivityComponent;
    }



    public void selectFragment(){
        presenter.addFragment();
        //addFragment(R.id.content_main,baseMVPFragment);

    }

    @Override
    public void showMessage(String message) {

    }
}
