package com.asudevelopers.financemanager.mvp.view;

import com.asudevelopers.financemanager.base.BaseView;
import com.asudevelopers.financemanager.mvp.model.entity.account.Account;

import java.util.List;

public interface AccountsView extends BaseView {

    void showAccounts(List<Account> accounts);
}
