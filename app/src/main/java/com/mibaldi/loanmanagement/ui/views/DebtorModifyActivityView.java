package com.mibaldi.loanmanagement.ui.views;


import com.mibaldi.loanmanagement.base.BaseView;

public interface DebtorModifyActivityView extends BaseView {
    void showProgress();

    void hideProgress();

    void setupView(String name,String email);
}
