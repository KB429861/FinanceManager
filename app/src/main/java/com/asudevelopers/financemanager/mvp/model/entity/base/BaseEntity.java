package com.asudevelopers.financemanager.mvp.model.entity.base;

import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
