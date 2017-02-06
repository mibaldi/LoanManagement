package com.mibaldi.loanmanagement.domain.features.debtorList;

import com.mibaldi.loanmanagement.data.models.Debtor;
import com.mibaldi.loanmanagement.data.repositories.DebtorDataRepository;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class DebtorListInteractorImpl implements DebtorListInteractor {
    public DebtorDataRepository debtorDataRepository;
    @Inject
    public DebtorListInteractorImpl(DebtorDataRepository debtorDataRepository){
        this.debtorDataRepository = debtorDataRepository;
    }

    @Override
    public void getDebtorList(CallbackListener<List<Debtor>> callbackListener) {
            debtorDataRepository.getDebtorList(callbackListener);
    }

    @Override
    public void setCurrentDebtor(Debtor debtor) {
        debtorDataRepository.setCurrentDebtor(debtor);
    }
}
