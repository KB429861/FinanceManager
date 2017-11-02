package com.asudevelopers.financemanager.mvp.model.entity.transaction;

import android.arch.persistence.room.ColumnInfo;

public abstract class PersonTransaction extends Transaction {

    @ColumnInfo(name = "person_id")
    private int personId;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
