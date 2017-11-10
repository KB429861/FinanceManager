package com.asudevelopers.financemanager.mvp.view;

import com.asudevelopers.financemanager.mvp.model.entity.transaction.PersonTransaction;
import com.asudevelopers.financemanager.mvp.view.base.BaseView;

public interface PersonTransactionView extends BaseView {

    void showTransaction(PersonTransaction transaction);
}
