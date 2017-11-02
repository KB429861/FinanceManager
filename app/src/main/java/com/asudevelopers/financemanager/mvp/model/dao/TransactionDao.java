package com.asudevelopers.financemanager.mvp.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.asudevelopers.financemanager.mvp.model.entity.transaction.LendTransaction;

@Dao
public interface TransactionDao {

//    @Query("select * from person_transactions where person_id = :personId")
//    Flowable<List<PersonTransaction>> selectPersonTransactions(int personId);

    @Insert
    void insertLendTransactions(LendTransaction... transactions);

    @Update
    void updateLendTransactions(LendTransaction... transactions);

    @Delete
    void deleteLendTransactions(LendTransaction... transactions);
}
