package com.mibaldi.loanmanagement.domain.callbacks;

public interface CallbackListener<T> {
    void onSuccess(T result);
    void onError(MyError myError);
}
