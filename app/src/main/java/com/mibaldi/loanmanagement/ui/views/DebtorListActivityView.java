package com.mibaldi.loanmanagement.ui.views;


import com.mibaldi.loanmanagement.base.BaseView;
import com.mibaldi.loanmanagement.data.models.Debtor;
import com.mibaldi.loanmanagement.ui.adapters.DebtorListAdapter;
import com.mibaldi.loanmanagement.ui.adapters.DebtorListFirebaseAdapter;

import java.util.List;

public interface DebtorListActivityView extends BaseView {
    void showProgress();

    void hideProgress();

    void initializeAdapter(List<Debtor> debtorList, DebtorListFirebaseAdapter.OnItemClickListener listener);
}
