package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.transaction.BorrowTransaction;
import com.asudevelopers.financemanager.mvp.model.entity.transaction.LendTransaction;
import com.asudevelopers.financemanager.mvp.model.entity.transaction.PersonTransaction;
import com.asudevelopers.financemanager.mvp.presenter.base.ItemPresenter;
import com.asudevelopers.financemanager.mvp.view.PersonTransactionView;

@InjectViewState
public class PersonTransactionPresenter
        extends ItemPresenter<PersonTransactionView, PersonTransaction> {

    public PersonTransactionPresenter(AppDatabase database) {
        super(database);
    }

    @Override
    protected void showItem(PersonTransaction transaction) {
        getViewState().showTransaction(transaction);
    }

    @Override
    protected void insertCommand(PersonTransaction transaction) {
        if (transaction instanceof LendTransaction) {
            database.transactions().insert((LendTransaction) transaction);
        } else if (transaction instanceof BorrowTransaction) {
            database.transactions().insert((BorrowTransaction) transaction);
        }
    }

    @Override
    protected void updateCommand(PersonTransaction transaction) {
        if (transaction instanceof LendTransaction) {
            database.transactions().update((LendTransaction) transaction);
        } else if (transaction instanceof BorrowTransaction) {
            database.transactions().update((BorrowTransaction) transaction);
        }
    }

    @Override
    protected void deleteCommand(PersonTransaction transaction) {
        if (item instanceof LendTransaction) {
            database.transactions().delete((LendTransaction) transaction);
        } else if (item instanceof BorrowTransaction) {
            database.transactions().delete((BorrowTransaction) transaction);
        }
    }
}
