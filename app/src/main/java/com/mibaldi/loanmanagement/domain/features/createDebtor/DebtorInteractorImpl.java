package com.mibaldi.loanmanagement.domain.features.createDebtor;

import com.google.firebase.auth.FirebaseUser;
import com.mibaldi.loanmanagement.data.models.Debtor;
import com.mibaldi.loanmanagement.data.repositories.DebtorDataRepository;
import com.mibaldi.loanmanagement.data.repositories.UserDataRepository;
import com.mibaldi.loanmanagement.domain.callbacks.CallbackListener;
import com.mibaldi.loanmanagement.domain.callbacks.ErrorConstants;
import com.mibaldi.loanmanagement.domain.callbacks.MyError;

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

    @Override
    public void modifyDebtor(String key, Map<String, Object> debtor, CallbackListener<Boolean> callbackListener) {
        debtorDataRepository.modifyDebtor(key,debtor,callbackListener);
    }

    @Override
    public void getCurrentDebtor(CallbackListener<Debtor> listener) {

        Debtor currentDebtor = debtorDataRepository.getCurrentDebtor();
        if (currentDebtor != null){
            listener.onSuccess(currentDebtor);
        }else {
            MyError error = new MyError();
            error.setDescription("Debtor null");
            error.setError(ErrorConstants.ERROR_404);
            listener.onError(error);
        }
    }
}
