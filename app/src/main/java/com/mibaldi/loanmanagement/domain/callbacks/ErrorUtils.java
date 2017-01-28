package com.mibaldi.loanmanagement.domain.callbacks;

import android.content.Context;
import android.util.Pair;

import com.mibaldi.loanmanagement.R;


public class ErrorUtils {

    public static Pair<String, String> getAlertError(MyError myError, Context context){
        String title, description;
        switch(myError.getError()){
            case ERROR_INTERNET_CONNECTON:
                title = context.getString(R.string.error_connection_title);
                description = context.getString(R.string.error_connection_description);
                break;
            default:
                title = context.getString(R.string.error_server_title);
                description = context.getString(R.string.error_server_description);
                break;
        }
        return new Pair(title, description);
    }
}
