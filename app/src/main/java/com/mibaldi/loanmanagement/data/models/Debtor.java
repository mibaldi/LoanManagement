package com.mibaldi.loanmanagement.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Debtor implements Parcelable {
    private String name;
    private String email;
    private List<Money> debtList;

    public Debtor(String name, String email, List<Money> debtList) {
        this.name = name;
        this.email = email;
        this.debtList = debtList;
    }
    public Debtor(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Debtor() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Money> getDebtList() {
        return debtList;
    }

    public void setDebtList(List<Money> debtList) {
        this.debtList = debtList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeList(this.debtList);
    }

    protected Debtor(Parcel in) {
        this.name = in.readString();
        this.email = in.readString();
        this.debtList = new ArrayList<Money>();
        in.readList(this.debtList, Money.class.getClassLoader());
    }

    public static final Parcelable.Creator<Debtor> CREATOR = new Parcelable.Creator<Debtor>() {
        @Override
        public Debtor createFromParcel(Parcel source) {
            return new Debtor(source);
        }

        @Override
        public Debtor[] newArray(int size) {
            return new Debtor[size];
        }
    };
}
