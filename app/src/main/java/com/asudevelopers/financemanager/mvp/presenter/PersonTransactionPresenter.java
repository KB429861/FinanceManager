package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.transaction.PersonTransaction;
import com.asudevelopers.financemanager.mvp.presenter.base.DatabasePresenter;
import com.asudevelopers.financemanager.mvp.view.PersonTransactionView;

@InjectViewState
public class PersonTransactionPresenter extends DatabasePresenter<PersonTransactionView> {

    private PersonTransaction transaction = null;

    public PersonTransactionPresenter(AppDatabase database) {
        super(database);
    }

    public void setPersonTransaction(PersonTransaction transaction) {
    }

    public void saveTransaction(PersonTransaction transaction) {

    }
}
