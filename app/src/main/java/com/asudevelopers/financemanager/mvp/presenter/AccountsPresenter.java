package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.Account;
import com.asudevelopers.financemanager.mvp.presenter.base.ItemsPresenter;
import com.asudevelopers.financemanager.mvp.view.AccountsView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class AccountsPresenter extends ItemsPresenter<AccountsView, Account> {

    public AccountsPresenter(AppDatabase database) {
        super(database);
    }

    @Override
    protected void showItems() {
        database.accounts().select()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        accounts -> {
                            setItems(accounts);
                            getViewState().showAccounts(accounts);
                        },
                        throwable -> getViewState().showError(throwable));
    }
}
