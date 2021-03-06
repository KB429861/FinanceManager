package com.asudevelopers.financemanager.mvp.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.asudevelopers.financemanager.mvp.model.entity.Currency;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface CurrencyDao {

    @Query("select * from currencies")
    Flowable<List<Currency>> select();

    @Query("select * from currencies where id = :currencyId")
    Flowable<Currency> select(int currencyId);

    @Query("select * from currencies where char_code = :charCode")
    Maybe<Currency> select(String charCode);

    @Insert
    void insert(Currency... currencies);

    @Update
    void update(Currency... currencies);

    @Delete
    void delete(Currency... currencies);
}
