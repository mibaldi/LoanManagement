package com.mibaldi.loanmanagement.ui.views;


import com.mibaldi.loanmanagement.base.BaseView;

public interface MainActivityView extends BaseView {
    void showLogout();
    void showUserInfo(String username,String picture);

}
