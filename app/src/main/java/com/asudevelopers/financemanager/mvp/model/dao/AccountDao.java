package com.asudevelopers.financemanager.mvp.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.asudevelopers.financemanager.mvp.model.entity.account.Account;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface AccountDao {

    @Query("select * from accounts")
    Flowable<List<Account>> select();

    @Query("select * from accounts where id = :accountId")
    Flowable<Account> select(int accountId);

    @Insert
    void insert(Account... accounts);

    @Update
    void update(Account... accounts);

    @Delete
    void delete(Account... accounts);
}
