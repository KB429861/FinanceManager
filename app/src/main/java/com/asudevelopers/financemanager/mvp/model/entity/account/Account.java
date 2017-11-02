package com.asudevelopers.financemanager.mvp.model.entity.account;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

import com.asudevelopers.financemanager.mvp.model.entity.base.BaseEntity;
import com.asudevelopers.financemanager.mvp.model.entity.currency.Currency;

@Entity(tableName = "accounts",
        foreignKeys = {
                @ForeignKey(
                        entity = Currency.class,
                        parentColumns = "id",
                        childColumns = "currency_id")},
        indices = {
                @Index("currency_id")})
public class Account extends BaseEntity {

    private String name;
    private double amount;

    @ColumnInfo(name = "currency_id")
    private int currencyId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
