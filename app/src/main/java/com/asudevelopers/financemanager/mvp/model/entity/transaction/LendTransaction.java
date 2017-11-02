package com.asudevelopers.financemanager.mvp.model.entity.transaction;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

import com.asudevelopers.financemanager.mvp.model.entity.account.Account;
import com.asudevelopers.financemanager.mvp.model.entity.currency.Currency;
import com.asudevelopers.financemanager.mvp.model.entity.person.Person;

@Entity(tableName = "lend_transactions",
        foreignKeys = {
                @ForeignKey(
                        entity = Account.class,
                        parentColumns = "id",
                        childColumns = "account_id"),
                @ForeignKey(
                        entity = Currency.class,
                        parentColumns = "id",
                        childColumns = "currency_id"),
                @ForeignKey(
                        entity = Person.class,
                        parentColumns = "id",
                        childColumns = "person_id")},
        indices = {
                @Index("account_id"),
                @Index("currency_id"),
                @Index("person_id")})
public class LendTransaction extends PersonTransaction {
}
