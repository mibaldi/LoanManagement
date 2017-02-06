package com.mibaldi.loanmanagement.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.mibaldi.loanmanagement.R;
import com.mibaldi.loanmanagement.base.baseMosby.activity.BaseMVPActivity;
import com.mibaldi.loanmanagement.data.models.Debtor;
import com.mibaldi.loanmanagement.di.HasComponent;
import com.mibaldi.loanmanagement.ui.adapters.DebtorListAdapter;
import com.mibaldi.loanmanagement.ui.presenters.debtorListActivity.DaggerDebtorListActivityComponent;
import com.mibaldi.loanmanagement.ui.presenters.debtorListActivity.DebtorListActivityComponent;
import com.mibaldi.loanmanagement.ui.presenters.debtorListActivity.DebtorListActivityPresenter;
import com.mibaldi.loanmanagement.ui.presenters.debtorModifyActivity.DaggerDebtorModifyActivityComponent;
import com.mibaldi.loanmanagement.ui.presenters.debtorModifyActivity.DebtorModifyActivityComponent;
import com.mibaldi.loanmanagement.ui.presenters.debtorModifyActivity.DebtorModifyActivityPresenter;
import com.mibaldi.loanmanagement.ui.views.DebtorListActivityView;
import com.mibaldi.loanmanagement.ui.views.DebtorModifyActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class DebtorListActivity extends BaseMVPActivity<DebtorListActivityPresenter, DebtorListActivityView>
        implements DebtorListActivityView, HasComponent<DebtorListActivityComponent> {

    private DebtorListActivityComponent debtorListActivityComponent;
    private ProgressDialog progressDialog;

    @BindView(R.id.rv_debtorlist)
    RecyclerView debtorListView;
    private DebtorListAdapter debtorListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initializeInjector();
        setContentView(R.layout.activity_debtor_list);

        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(getClass().getName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Cargando...");

        debtorListAdapter = new DebtorListAdapter();
        debtorListView.setLayoutManager(new LinearLayoutManager(this));
        debtorListView.setAdapter(debtorListAdapter);
        presenter.init(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.refreshList();
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
    @NonNull
    @Override
    public DebtorListActivityPresenter createPresenter() {
        return debtorListActivityComponent.presenter();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public DebtorListActivityComponent getComponent() {
        return debtorListActivityComponent;
    }

    public void initializeInjector() {
        this.debtorListActivityComponent = DaggerDebtorListActivityComponent.builder().loanManagementApplicationComponent(getInjector()).build();
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

    @Override
    public void initializeAdapter(List<Debtor> debtorList, DebtorListAdapter.OnItemClickListener listener) {
        debtorListAdapter.setDataAndListener(debtorList,listener);
    }
}
