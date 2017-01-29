package com.mibaldi.loanmanagement.router;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;


import com.mibaldi.loanmanagement.ui.activities.MainActivity;
import com.mibaldi.loanmanagement.ui.activities.LoginActivity;

import javax.inject.Inject;

public class Router {
    private Context context;
    @Inject
    public Router(Context context) {
        this.context = context;
    }

    public void finishActivity(Context activityContext) {
        if (activityContext != null) {
            ((AppCompatActivity) (activityContext)).finish();
        }
    }
    public void goToLoginActivity(){
        if (context != null){
            Intent intent = LoginActivity.getCallingIntent(context,LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public void goToMainActivity() {
        if (context != null){
            Intent intent = MainActivity.getCallingIntent(context,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
