package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.Account;
import com.asudevelopers.financemanager.mvp.presenter.base.ItemPresenter;
import com.asudevelopers.financemanager.mvp.view.AccountView;

@InjectViewState
public class AccountPresenter extends ItemPresenter<AccountView, Account> {

    public AccountPresenter(AppDatabase database) {
        super(database);
    }

    @Override
    protected void showItem(Account account) {
        getViewState().showAccount(account);
    }

    @Override
    protected void insertCommand(Account account) {
        database.accounts().insert(account);
    }

    @Override
    protected void updateCommand(Account account) {
        database.accounts().update(account);
    }

    @Override
    protected void deleteCommand(Account account) {
        database.accounts().delete(account);
    }
}
