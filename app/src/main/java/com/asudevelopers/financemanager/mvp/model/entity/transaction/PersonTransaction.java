package com.asudevelopers.financemanager.mvp.model.entity.transaction;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

import com.asudevelopers.financemanager.mvp.model.entity.person.Person;

@Entity(tableName = "person_transactions",
        foreignKeys = @ForeignKey(
                entity = Person.class,
                parentColumns = "id",
                childColumns = "person_id"),
        indices = @Index("person_id"))
public class PersonTransaction extends Transaction {

    @ColumnInfo(name = "person_id")
    private int personId;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
