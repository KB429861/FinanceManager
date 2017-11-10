package com.asudevelopers.financemanager.mvp.view;

import com.asudevelopers.financemanager.mvp.model.entity.account.Account;
import com.asudevelopers.financemanager.mvp.view.base.BaseView;

import java.util.List;

public interface AccountsView extends BaseView {

    void showAccounts(List<Account> accounts);
}
