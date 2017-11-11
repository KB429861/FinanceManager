package com.asudevelopers.financemanager.mvp.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.asudevelopers.financemanager.mvp.model.entity.person.Person;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface PersonDao {

    @Query("select * from people")
    Flowable<List<Person>> select();

    @Query("select * from people where id = :personId")
    Flowable<Person> select(int personId);

    @Insert
    void insert(Person... people);

    @Update
    void update(Person... people);

    @Delete
    void delete(Person... people);
}
