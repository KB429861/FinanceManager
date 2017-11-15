package com.asudevelopers.financemanager.mvp.presenter;

import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.Person;
import com.asudevelopers.financemanager.mvp.presenter.base.ItemsPresenter;
import com.asudevelopers.financemanager.mvp.view.PeopleView;

public class PeopleSummaryPresenter extends ItemsPresenter<PeopleView, Person> {

    public PeopleSummaryPresenter(AppDatabase database) {
        super(database);
    }

    @Override
    protected void showItems() {

    }
}
