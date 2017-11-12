package com.asudevelopers.financemanager.mvp.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;

import com.asudevelopers.financemanager.mvp.model.entity.base.BaseEntity;

@Entity(tableName = "accounts",
        foreignKeys = {
                @ForeignKey(
                        entity = Currency.class,
                        parentColumns = "char_code",
                        childColumns = "currency_char_code",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)},
        indices = {
                @Index("currency_char_code")})
public class Account extends BaseEntity {

    private String name;
    private double amount;

    @ColumnInfo(name = "currency_char_code")
    private String currencyCharCode;

    public Account() {
    }

    @Ignore
    public Account(String name, double amount, String currencyCharCode) {
        this.name = name;
        this.amount = amount;
        this.currencyCharCode = currencyCharCode;
    }

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

    public String getCurrencyCharCode() {
        return currencyCharCode;
    }

    public void setCurrencyCharCode(String currencyCharCode) {
        this.currencyCharCode = currencyCharCode;
    }
}
