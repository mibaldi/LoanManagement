package com.mibaldi.loanmanagement.domain.callbacks;

public class MyError {
    private ErrorConstants error;
    private String description = "";

    public MyError() {}

    public ErrorConstants getError() {
        return error;
    }

    public void setError(ErrorConstants error) {
        this.error = error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
