package com.asudevelopers.financemanager.mvp.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;

import com.asudevelopers.financemanager.mvp.model.entity.base.BaseEntity;

@Entity(tableName = "currencies",
        indices = @Index(value = "char_code", unique = true))
public class Currency extends BaseEntity {

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
        return name;
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
