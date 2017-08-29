package com.asudevelopers.financemanager.database.contract;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.asudevelopers.financemanager.database.model.PersonModel;

import java.util.ArrayList;
import java.util.List;

public abstract class PersonContract {

    private static final String TABLE_NAME = "people";
    private static final String COLUMN_NAME_ID = "id";
    private static final String COLUMN_NAME_NAME = "name";
    private static final String COLUMN_NAME_PHONE = "phone";

    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_NAME_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_NAME_NAME + " TEXT,"
                    + COLUMN_NAME_PHONE + " TEXT" + ")";

    private static final String DROP_TABLE_SQL =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    private static final String SELECT_ALL_SQL =
            "SELECT * FROM " + TABLE_NAME;

    public static void createTable(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_SQL);
    }

    public static void dropTable(SQLiteDatabase database) {
        database.execSQL(DROP_TABLE_SQL);
    }

    public static void insertPerson(SQLiteDatabase database, PersonModel person) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_NAME, person.getName());
        values.put(COLUMN_NAME_PHONE, person.getPhone());
        database.insert(TABLE_NAME, null, values);
        database.close();
    }

    public static PersonModel selectPerson(SQLiteDatabase database, int id) {
        Cursor cursor = database.query(
                TABLE_NAME,
                new String[]{COLUMN_NAME_ID, COLUMN_NAME_NAME, COLUMN_NAME_PHONE},
                COLUMN_NAME_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            PersonModel person = new PersonModel();
            person.setId(Integer.parseInt(cursor.getString(0)));
            person.setName(cursor.getString(1));
            person.setPhone(cursor.getString(2));
            cursor.close();
            return person;
        }
        return null;
    }

    public static List<PersonModel> selectPeople(SQLiteDatabase database) {
        List<PersonModel> people = new ArrayList<>();
        Cursor cursor = database.rawQuery(SELECT_ALL_SQL, null);
        if (cursor.moveToFirst()) {
            do {
                PersonModel person = new PersonModel();
                person.setId(Integer.parseInt(cursor.getString(0)));
                person.setName(cursor.getString(1));
                person.setPhone(cursor.getString(2));
                people.add(person);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return people;
    }
}
