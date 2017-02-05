package com.mibaldi.loanmanagement.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.mibaldi.loanmanagement.R;
import com.mibaldi.loanmanagement.base.baseMosby.activity.BaseMVPActivity;
import com.mibaldi.loanmanagement.di.HasComponent;
import com.mibaldi.loanmanagement.ui.presenters.debtorModifyActivity.DaggerDebtorModifyActivityComponent;
import com.mibaldi.loanmanagement.ui.presenters.debtorModifyActivity.DebtorModifyActivityComponent;
import com.mibaldi.loanmanagement.ui.presenters.debtorModifyActivity.DebtorModifyActivityPresenter;
import com.mibaldi.loanmanagement.ui.presenters.loginActivity.DaggerLoginActivityComponent;
import com.mibaldi.loanmanagement.ui.presenters.loginActivity.LoginActivityComponent;
import com.mibaldi.loanmanagement.ui.presenters.loginActivity.LoginActivityPresenter;
import com.mibaldi.loanmanagement.ui.views.DebtorModifyActivityView;
import com.mibaldi.loanmanagement.ui.views.LoginActivityView;

import butterknife.BindView;
import butterknife.OnClick;

import static com.mibaldi.loanmanagement.utils.Constants.GOOGLE_SIGN_IN;


public class DebtorModifyActivity extends BaseMVPActivity<DebtorModifyActivityPresenter, DebtorModifyActivityView>
        implements DebtorModifyActivityView, HasComponent<DebtorModifyActivityComponent> {

    @BindView(R.id.tv_debtorName)
    EditText debtorName;
    @BindView(R.id.tv_debtorEmail)
    EditText debtorEmail;

    private DebtorModifyActivityComponent debtorModifyActivityComponent;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initializeInjector();
        setContentView(R.layout.activity_debtor_modify);

        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        if (getIntent().getExtras() != null){
            presenter.init(this,getIntent().getExtras().getBoolean("update"));
        }
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Cargando...");

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }
    @OnClick(R.id.btn_save_debtor)
    public void save(){
        presenter.createDebtor(debtorName.getText().toString(),debtorEmail.getText().toString());
    }
    @NonNull
    @Override
    public DebtorModifyActivityPresenter createPresenter() {
        return debtorModifyActivityComponent.presenter();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public DebtorModifyActivityComponent getComponent() {
        return debtorModifyActivityComponent;
    }

    public void initializeInjector() {
        this.debtorModifyActivityComponent = DaggerDebtorModifyActivityComponent.builder().loanManagementApplicationComponent(getInjector()).build();
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
