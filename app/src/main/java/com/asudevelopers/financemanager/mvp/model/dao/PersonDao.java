package com.asudevelopers.financemanager.mvp.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.asudevelopers.financemanager.mvp.model.entity.Person;

import java.util.List;

import io.reactivex.Flowable;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface PersonDao {

    @Query("select * from people")
    Flowable<List<Person>> selectPeople();

    @Query("select * from people where id = :personId")
    Flowable<Person> selectPerson(int personId);

    @Insert(onConflict = REPLACE)
    void insertPeople(Person... people);

    @Update
    void updatePeople(Person... people);

    @Delete
    void deletePeople(Person... people);
}
