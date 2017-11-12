package com.asudevelopers.financemanager.mvp.model.entity.transaction;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

import com.asudevelopers.financemanager.mvp.model.entity.Account;
import com.asudevelopers.financemanager.mvp.model.entity.Currency;
import com.asudevelopers.financemanager.mvp.model.entity.Person;

@Entity(tableName = "borrow_transactions",
        foreignKeys = {
                @ForeignKey(
                        entity = Account.class,
                        parentColumns = "id",
                        childColumns = "account_id",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(
                        entity = Currency.class,
                        parentColumns = "char_code",
                        childColumns = "currency_char_code",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(
                        entity = Person.class,
                        parentColumns = "id",
                        childColumns = "person_id",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)},
        indices = {
                @Index("account_id"),
                @Index("currency_char_code"),
                @Index("person_id")})
public class BorrowTransaction extends PersonTransaction {
}
