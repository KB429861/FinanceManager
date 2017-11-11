package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
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
    protected void insertCommand(PersonTransaction item) {

    }

    @Override
    protected void updateCommand(PersonTransaction item) {

    }

    @Override
    protected void deleteCommand() {

    }
}
