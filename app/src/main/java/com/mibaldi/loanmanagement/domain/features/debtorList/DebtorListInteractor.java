package com.mibaldi.loanmanagement.domain.features.debtorList;

import com.mibaldi.loanmanagement.data.models.Debtor;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;

import java.util.List;
import java.util.Map;

public interface DebtorListInteractor {
    void getDebtorList(CallbackListener<List<Debtor>> callbackListener);

    void setCurrentDebtor(Debtor debtor);
}
