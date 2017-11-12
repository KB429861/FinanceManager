package com.asudevelopers.financemanager.mvp.view;

import com.asudevelopers.financemanager.mvp.model.entity.Account;
import com.asudevelopers.financemanager.mvp.view.base.BaseView;

public interface AccountView extends BaseView {

    void showAccount(Account account);
}
