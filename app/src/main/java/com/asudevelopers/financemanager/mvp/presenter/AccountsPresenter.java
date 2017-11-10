package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.asudevelopers.financemanager.base.BasePresenter;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.account.Account;
import com.asudevelopers.financemanager.mvp.view.AccountsView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class AccountsPresenter extends BasePresenter<AccountsView> {

    private List<Account> accounts;

    public AccountsPresenter(AppDatabase database) {
        super(database);
    }

    public void loadAccounts() {
        database.accountDao().selectAccounts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<List<Account>>() {
                            @Override
                            public void accept(List<Account> accounts) {
                                setAccounts(accounts);
                                getViewState().showAccounts(accounts);
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                getViewState().showError(throwable);
                            }
                        });
    }

    private void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Account getAccount(int position) {
        return accounts.get(position);
    }
}
