package com.mibaldi.loanmanagement.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mibaldi.loanmanagement.R;
import com.mibaldi.loanmanagement.data.models.Debtor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DebtorListAdapter extends RecyclerView.Adapter<DebtorListAdapter.DebtorListViewHolder> {

    private List<Debtor> listItem = new ArrayList<>();
    private OnItemClickListener listener;

    @Inject
    public DebtorListAdapter(){}

    @Override
    public DebtorListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_debtor_list, parent, false);
        return new DebtorListViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(DebtorListViewHolder holder, int position) {
        holder.bindItem(listItem.get(position));
    }

    @Override
    public int getItemCount() {
        return this.listItem.size();
    }

    public interface OnItemClickListener {
        void openDebtorDetail(Debtor debtor);
    }

    public void setDataAndListener(List<Debtor> debtorList, OnItemClickListener listener) {
        this.listItem = debtorList;
        this.listener = listener;
        this.notifyDataSetChanged();
    }

    public class DebtorListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_debtorName)
        TextView debtorItemName;
        @BindView(R.id.tv_debtorEmail)
        TextView debtorItemEmail;
        public OnItemClickListener listener;
        private Debtor debtor;


        public DebtorListViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.listener = listener;
        }

        public void bindItem(Debtor debtor) {
            this.debtor = debtor;
            debtorItemName.setText(debtor.getName());
            debtorItemEmail.setText(debtor.getEmail());
            /*buildingName.setText(building.getName());
            String distance = StringFormat.truncate2Decimals(building.getDistance());
            buildingDistance.setText("A "+distance+" metros");
            Glide.with(itemView.getContext()).load(building.getMainImage()).centerCrop().into(buildingImage);*/
        }

        @OnClick(R.id.item_debtor)
        public void openDebtor(){
            listener.openDebtorDetail(debtor);
        }

    }

}
