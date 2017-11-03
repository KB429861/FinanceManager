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
    Flowable<List<Account>> selectAccounts();

    @Query("select * from accounts where id = :accountId")
    Flowable<Account> selectAccount(int accountId);

    @Insert
    void insertAccounts(Account... accounts);

    @Update
    void updateAccounts(Account... accounts);

    @Delete
    void deleteAccounts(Account... accounts);
}
