package com.asudevelopers.financemanager.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.asudevelopers.financemanager.database.model.Person;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface PersonDao {

    @Query("SELECT * FROM people")
    List<Person> getAll();

    @Query("SELECT * FROM people WHERE id IN (:peopleIds)")
    List<Person> loadAllByIds(int[] peopleIds);

    @Query("SELECT * FROM people WHERE name LIKE :name LIMIT 1")
    Person findByName(String name);

    @Insert(onConflict = REPLACE)
    void insertAll(Person... people);

    @Delete
    void delete(Person person);
}
