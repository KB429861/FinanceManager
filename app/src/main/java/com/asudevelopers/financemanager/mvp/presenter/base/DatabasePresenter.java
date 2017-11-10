package com.asudevelopers.financemanager.mvp.presenter.base;

import com.arellomobile.mvp.MvpPresenter;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.view.base.BaseView;

public abstract class DatabasePresenter<T extends BaseView> extends MvpPresenter<T> {

    protected AppDatabase database;

    public DatabasePresenter(AppDatabase database) {
        this.database = database;
    }
}
