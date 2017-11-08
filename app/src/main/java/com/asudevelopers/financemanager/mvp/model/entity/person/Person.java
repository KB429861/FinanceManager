package com.asudevelopers.financemanager.mvp.model.entity.person;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

import com.asudevelopers.financemanager.mvp.model.entity.base.BaseEntity;

@Entity(tableName = "people")
public class Person extends BaseEntity {

    private String name;
    private String phone;

    public Person() {
    }

    @Ignore
    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
