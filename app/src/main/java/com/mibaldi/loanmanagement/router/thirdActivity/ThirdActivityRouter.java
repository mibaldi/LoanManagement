package com.mibaldi.loanmanagement.router.thirdActivity;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.mibaldi.loanmanagement.R;
import com.mibaldi.loanmanagement.base.baseMosby.activity.BaseMVPActivity;
import com.mibaldi.loanmanagement.base.baseMosby.fragment.BaseMVPFragment;
import com.mibaldi.loanmanagement.ui.activities.DebtorListActivity;
import com.mibaldi.loanmanagement.ui.activities.DebtorModifyActivity;
import com.mibaldi.loanmanagement.ui.activities.LoginActivity;
import com.mibaldi.loanmanagement.ui.activities.MainActivity;
import com.mibaldi.loanmanagement.ui.activities.ThirdActivity;
import com.mibaldi.loanmanagement.ui.fragments.FirstThirdFragment;
import com.mibaldi.loanmanagement.ui.fragments.SecondThirdFragment;

import javax.inject.Inject;

public class ThirdActivityRouter {

    private Context context;

    @Inject
    public ThirdActivityRouter(Context context) {
        this.context = context;
    }

    public void addFirstFragment(){
        FragmentTransaction fragmentTransaction = ((BaseMVPActivity)context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main, FirstThirdFragment.newInstance());
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void addSecondFragment() {
        FragmentTransaction fragmentTransaction = ((BaseMVPActivity)context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main, SecondThirdFragment.newInstance());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
