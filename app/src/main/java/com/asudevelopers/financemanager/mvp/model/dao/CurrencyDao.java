package com.asudevelopers.financemanager.mvp.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.asudevelopers.financemanager.mvp.model.entity.currency.Currency;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface CurrencyDao {

    @Query("select * from currencies")
    Flowable<List<Currency>> selectCurrencies();

    @Query("select * from currencies where id = :currencyId")
    Flowable<Currency> selectCurrency(int currencyId);

    @Query("select * from currencies where char_code = :charCode")
    Maybe<Currency> selectCurrency(String charCode);

    @Insert
    void insertCurrencies(Currency... currencies);

    @Update
    void updateCurrencies(Currency... currencies);

    @Delete
    void deleteCurrencies(Currency... currencies);
}
