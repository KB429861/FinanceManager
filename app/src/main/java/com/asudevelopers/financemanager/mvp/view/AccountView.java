package com.asudevelopers.financemanager.mvp.view;

import com.asudevelopers.financemanager.base.BaseView;
import com.asudevelopers.financemanager.mvp.model.entity.account.Account;

public interface AccountView extends BaseView {

    void showAccount(Account account);
}
