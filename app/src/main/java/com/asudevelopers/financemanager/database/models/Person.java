package com.asudevelopers.financemanager.database.models;

public class Person {

    public static final String TABLE_NAME = "person";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "";

    private int id;
    private String name;
    private String phone;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
