package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.Person;
import com.asudevelopers.financemanager.mvp.presenter.base.ItemPresenter;
import com.asudevelopers.financemanager.mvp.view.PersonView;

@InjectViewState
public class PersonPresenter extends ItemPresenter<PersonView, Person> {

    public PersonPresenter(AppDatabase database) {
        super(database);
    }

    @Override
    protected void showItem(Person item) {
        getViewState().showPerson(item);
    }

    @Override
    protected void insertCommand(Person person) {
        database.people().insert(person);
    }

    @Override
    protected void updateCommand(Person person) {
        database.people().update(person);
    }

    @Override
    protected void deleteCommand(Person person) {
        database.people().delete(person);
    }
}
