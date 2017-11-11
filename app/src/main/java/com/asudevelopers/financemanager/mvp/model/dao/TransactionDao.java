package com.asudevelopers.financemanager.mvp.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.asudevelopers.financemanager.mvp.model.entity.transaction.BorrowTransaction;
import com.asudevelopers.financemanager.mvp.model.entity.transaction.LendTransaction;

@Dao
public interface TransactionDao {

    @Insert
    void insert(LendTransaction... transactions);

    @Insert
    void insert(BorrowTransaction... transactions);

    @Update
    void update(LendTransaction... transactions);

    @Update
    void update(BorrowTransaction... transactions);

    @Delete
    void delete(LendTransaction... transactions);

    @Delete
    void delete(BorrowTransaction... transactions);
}
