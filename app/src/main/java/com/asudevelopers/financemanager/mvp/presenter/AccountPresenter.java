package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.account.Account;
import com.asudevelopers.financemanager.mvp.presenter.base.DatabasePresenter;
import com.asudevelopers.financemanager.mvp.view.AccountView;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class AccountPresenter extends DatabasePresenter<AccountView> {

    private Account account = null;

    public AccountPresenter(AppDatabase database) {
        super(database);
    }

    public void saveAccountInfo(final String name, final double amount, final String charCode) {
        Completable.fromAction(
                new Action() {
                    @Override
                    public void run() {
                        boolean isUpdate = true;
                        if (account == null) {
                            account = new Account();
                            isUpdate = false;
                        }
                        account.setName(name);
                        account.setAmount(amount);
                        account.setCurrencyCharCode(charCode);
                        if (isUpdate) {
                            database.accountDao().updateAccounts(account);
                        } else {
                            database.accountDao().insertAccounts(account);
                        }
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Action() {
                            @Override
                            public void run() {
                                getViewState().showMessage(R.string.msg_saved);
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                getViewState().showError(throwable);
                            }
                        });
    }

    public void loadAndShowAccount(Account account) {
        this.account = account;
        if (account != null) {
            getViewState().showAccount(account);
        }
    }
}
