package com.asudevelopers.financemanager.mvp.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.asudevelopers.financemanager.mvp.model.entity.transaction.BorrowTransaction;
import com.asudevelopers.financemanager.mvp.model.entity.transaction.LendTransaction;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface TransactionDao {

    @Query("select * from lend_transactions")
    Flowable<List<LendTransaction>> selectLendTransactions();

    @Query("select * from borrow_transactions")
    Flowable<List<BorrowTransaction>> selectBorrowTransactions();

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
