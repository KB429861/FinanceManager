package com.asudevelopers.financemanager.mvp.model.common;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.asudevelopers.financemanager.mvp.model.dao.AccountDao;
import com.asudevelopers.financemanager.mvp.model.dao.CurrencyDao;
import com.asudevelopers.financemanager.mvp.model.dao.PersonDao;
import com.asudevelopers.financemanager.mvp.model.dao.TransactionDao;
import com.asudevelopers.financemanager.mvp.model.entity.account.Account;
import com.asudevelopers.financemanager.mvp.model.entity.currency.Currency;
import com.asudevelopers.financemanager.mvp.model.entity.person.Person;
import com.asudevelopers.financemanager.mvp.model.entity.transaction.BorrowTransaction;
import com.asudevelopers.financemanager.mvp.model.entity.transaction.LendTransaction;
import com.asudevelopers.financemanager.util.converter.Converters;

@Database(
        entities = {
                Currency.class, Account.class, Person.class, LendTransaction.class,
                BorrowTransaction.class},
        version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "FinanceManager.db";

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).build();
        }
        return INSTANCE;
    }

    public abstract CurrencyDao currencies();

    public abstract PersonDao people();

    public abstract AccountDao accounts();

    public abstract TransactionDao transactions();
}
