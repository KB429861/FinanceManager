package com.asudevelopers.financemanager.mvp.model.entity.currency;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

import com.asudevelopers.financemanager.mvp.model.entity.base.BaseEntity;

@Entity(tableName = "currencies")
public class Currency extends BaseEntity {

    @Ignore
    private String name;

    @ColumnInfo(name = "char_code")
    private String charCode;

    private double value;

    public Currency() {
    }

    @Ignore
    public Currency(String name, String charCode, double value) {
        this.name = name;
        this.charCode = charCode;
        this.value = value;
    }

    public String getName() {
        return "DODOD";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
