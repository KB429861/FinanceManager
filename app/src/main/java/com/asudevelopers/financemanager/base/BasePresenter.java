package com.asudevelopers.financemanager.base;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;

public abstract class BasePresenter<T extends MvpView> extends MvpPresenter<T> {

    protected AppDatabase database;

    public BasePresenter(AppDatabase database) {
        this.database = database;
    }
}
