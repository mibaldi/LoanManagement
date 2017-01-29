package com.mibaldi.loanmanagement.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.widget.Toast;

import com.mibaldi.loanmanagement.R;
import com.mibaldi.loanmanagement.base.baseMosby.activity.BaseMVPActivity;
import com.mibaldi.loanmanagement.di.HasComponent;
import com.mibaldi.loanmanagement.ui.presenters.loginActivity.DaggerLoginActivityComponent;
import com.mibaldi.loanmanagement.ui.presenters.loginActivity.LoginActivityComponent;
import com.mibaldi.loanmanagement.ui.presenters.loginActivity.LoginActivityPresenter;
import com.mibaldi.loanmanagement.ui.views.SecondActivityView;

import butterknife.OnClick;
import icepick.State;

import static com.mibaldi.loanmanagement.utils.Constants.GOOGLE_SIGN_IN;


public class LoginActivity extends BaseMVPActivity<LoginActivityPresenter, SecondActivityView>
        implements SecondActivityView, HasComponent<LoginActivityComponent> {


    private LoginActivityComponent loginActivityComponent;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initializeInjector();
        setContentView(R.layout.activity_second);

        super.onCreate(savedInstanceState);
        presenter.init(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Cargando...");

    }
    @OnClick(R.id.sign_in_button)
    public void signIn() {
        presenter.login();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGN_IN) {
            presenter.signInResult(data);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.addAuthState();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (progressDialog != null){
            progressDialog.dismiss();
        }
        presenter.removeAuthState();
    }

    @NonNull
    @Override
    public LoginActivityPresenter createPresenter() {
        return loginActivityComponent.presenter();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public LoginActivityComponent getComponent() {
        return loginActivityComponent;
    }

    public void initializeInjector() {
        this.loginActivityComponent = DaggerLoginActivityComponent.builder().loanManagementApplicationComponent(getInjector()).build();
    }


    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }
}
