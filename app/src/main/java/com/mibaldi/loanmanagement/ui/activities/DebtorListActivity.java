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

import com.firebase.ui.database.FirebaseIndexRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mibaldi.loanmanagement.R;
import com.mibaldi.loanmanagement.base.baseMosby.activity.BaseMVPActivity;
import com.mibaldi.loanmanagement.data.models.Debtor;
import com.mibaldi.loanmanagement.di.HasComponent;
import com.mibaldi.loanmanagement.ui.adapters.DebtorListAdapter;
import com.mibaldi.loanmanagement.ui.adapters.DebtorListFirebaseAdapter;
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
    private LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);

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

        attachRecyclerViewAdapter();
        presenter.init(this);

    }
    private void attachRecyclerViewAdapter() {

        debtorListView.setLayoutManager(linearLayoutManager);
        //debtorListView.setAdapter(debtorListAdapter);


       /* Query keyRef = FirebaseDatabase.getInstance().getReference("UsersDebtors").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        Query valueRef = FirebaseDatabase.getInstance().getReference("Debtors");
        final FirebaseIndexRecyclerAdapter<Debtor, DebtorListAdapter.DebtorListViewHolder> firebaseIndexRecyclerAdapter = new FirebaseIndexRecyclerAdapter<Debtor, DebtorListAdapter.DebtorListViewHolder>(Debtor.class, R.layout.item_debtor_list, DebtorListAdapter.DebtorListViewHolder.class, keyRef, valueRef) {
            @Override
            protected void populateViewHolder(DebtorListAdapter.DebtorListViewHolder viewHolder, Debtor model, int position) {

            }

            @Override
            protected void onDataChanged() {
                super.onDataChanged();
            }
        };*/
       /* firebaseIndexRecyclerAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                linearLayoutManager.smoothScrollToPosition(debtorListView,null,firebaseIndexRecyclerAdapter.getItemCount());
            }
        });
        debtorListView.setAdapter(firebaseIndexRecyclerAdapter);*/

       /* Query lastFifty = mChatRef.limitToLast(50);
        mRecyclerViewAdapter = new FirebaseRecyclerAdapter<Chat, ChatHolder>(
                Chat.class, R.layout.message, ChatHolder.class, lastFifty) {

            @Override
            public void populateViewHolder(ChatHolder chatView, Chat chat, int position) {
                chatView.setName(chat.getName());
                chatView.setText(chat.getMessage());

                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser != null && chat.getUid().equals(currentUser.getUid())) {
                    chatView.setIsSender(true);
                } else {
                    chatView.setIsSender(false);
                }
            }

            @Override
            protected void onDataChanged() {
                // if there are no chat messages, show a view that invites the user to add a message
                mEmptyListView.setVisibility(mRecyclerViewAdapter.getItemCount() == 0 ? View.VISIBLE : View.INVISIBLE);
            }
        };

        // Scroll to bottom on new messages
        mRecyclerViewAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                mManager.smoothScrollToPosition(mMessages, null, mRecyclerViewAdapter.getItemCount());
            }
        });

        debtorListView.setAdapter(mRecyclerViewAdapter);*/
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
    public void initializeAdapter(List<Debtor> debtorList, DebtorListFirebaseAdapter.OnItemClickListener listener) {
        final DebtorListFirebaseAdapter firebaseIndexRecyclerAdapter = new DebtorListFirebaseAdapter(Debtor.class,R.layout.item_debtor_list, DebtorListFirebaseAdapter.DebtorListViewHolder.class,listener);
        firebaseIndexRecyclerAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                linearLayoutManager.smoothScrollToPosition(debtorListView,null,firebaseIndexRecyclerAdapter.getItemCount());
            }
        });
        debtorListView.setAdapter(firebaseIndexRecyclerAdapter);
        //debtorListAdapter.setDataAndListener(debtorList,listener);
    }
}
