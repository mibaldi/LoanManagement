package com.mibaldi.loanmanagement.data.validators;

import com.mibaldi.loanmanagement.data.models.Debtor;

public class DebtorValidator {
    public static boolean validateCreator(Debtor debtor){
        boolean canCreate = true;
        boolean nameValidation = true;
        boolean emailValidation = true;
        if (debtor.getName() == null || debtor.getName().isEmpty()){
            nameValidation = false;
        }
        if (debtor.getEmail() == null || debtor.getEmail().isEmpty()){
            emailValidation = false;
        }
        canCreate = nameValidation && emailValidation;
        return canCreate;
    }
}
