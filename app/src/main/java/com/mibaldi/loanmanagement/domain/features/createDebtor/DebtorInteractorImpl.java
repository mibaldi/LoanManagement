package com.mibaldi.loanmanagement.domain.features.createDebtor;

import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.loanmanagement.data.repositories.DebtorDataRepository;
import com.mibaldi.loanmanagement.data.repositories.UserDataRepository;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;

import java.util.Map;

import javax.inject.Inject;

public class DebtorInteractorImpl implements DebtorInteractor {
    public DebtorDataRepository debtorDataRepository;
    @Inject
    public DebtorInteractorImpl(DebtorDataRepository debtorDataRepository){
        this.debtorDataRepository = debtorDataRepository;
    }

    @Override
    public void createDebtor(Map<String, Object> debtor,CallbackListener<String> callbackListener) {
            debtorDataRepository.createDebtor(debtor,callbackListener);
    }
}
