package com.mibaldi.loanmanagement.ui.adapters;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseIndexRecyclerAdapter;
import com.google.firebase.database.Query;
import com.mibaldi.loanmanagement.R;
import com.mibaldi.loanmanagement.data.FirebaseReferences;
import com.mibaldi.loanmanagement.data.models.Debtor;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DebtorListFirebaseAdapter extends FirebaseIndexRecyclerAdapter<Debtor,DebtorListFirebaseAdapter.DebtorListViewHolder> {

    private OnItemClickListener listener;

    /**
     * @param modelClass      Firebase will marshall the data at a location into an instance
     *                        of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list.
     *                        You will be responsible for populating an
     *                        instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     */
    public DebtorListFirebaseAdapter(Class<Debtor> modelClass, @LayoutRes int modelLayout, Class<DebtorListViewHolder> viewHolderClass,OnItemClickListener listener) {
        super(modelClass, modelLayout, viewHolderClass, FirebaseReferences.getUsersDebtorsReferences(), FirebaseReferences.getDebtorsReferences());
        this.listener = listener;
    }

    @Override
    protected void onDataChanged() {
        super.onDataChanged();
    }

    @Override
    protected void populateViewHolder(DebtorListViewHolder viewHolder, Debtor model, int position) {
        viewHolder.bindListener(listener);
        viewHolder.bindItem(model);
    }
    public static class DebtorListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_debtorName)
        TextView debtorItemName;
        @BindView(R.id.tv_debtorEmail)
        TextView debtorItemEmail;
        public OnItemClickListener listener;
        private Debtor debtor;


        public DebtorListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
        public void bindListener (OnItemClickListener listener){
            this.listener = listener;
        }

        public void bindItem(Debtor debtor) {
            this.debtor = debtor;
            debtorItemName.setText(debtor.getName());
            debtorItemEmail.setText(debtor.getEmail());
        }

        @OnClick(R.id.item_debtor)
        public void openDebtor(){
            listener.openDebtorDetail(debtor);
        }

    }
    public interface OnItemClickListener {
        void openDebtorDetail(Debtor debtor);
    }
}
