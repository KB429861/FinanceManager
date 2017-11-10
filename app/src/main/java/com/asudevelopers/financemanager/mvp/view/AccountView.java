package com.asudevelopers.financemanager.mvp.view;

import com.asudevelopers.financemanager.mvp.model.entity.account.Account;
import com.asudevelopers.financemanager.mvp.view.base.BaseView;

public interface AccountView extends BaseView {

    void showAccount(Account account);
}
