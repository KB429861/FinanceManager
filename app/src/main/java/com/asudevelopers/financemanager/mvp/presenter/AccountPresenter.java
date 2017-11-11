package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.account.Account;
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
        database.accountDao().insertAccounts(account);
    }

    @Override
    protected void updateCommand(Account account) {
        database.accountDao().updateAccounts(account);
    }

    @Override
    protected void deleteCommand() {
        database.accountDao().deleteAccounts(item);
    }
}
