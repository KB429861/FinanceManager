package com.asudevelopers.financemanager.mvp.model.entity.transaction;

import android.arch.persistence.room.ColumnInfo;

import com.asudevelopers.financemanager.mvp.model.entity.base.BaseEntity;

import java.util.Date;

public abstract class Transaction extends BaseEntity {

    private Date date;
    private double amount;

    @ColumnInfo(name = "currency_id")
    private int currencyId;

    @ColumnInfo(name = "account_id")
    private int accountId;

    private String description;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
