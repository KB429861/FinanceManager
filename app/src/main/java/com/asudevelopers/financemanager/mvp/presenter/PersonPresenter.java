package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.person.Person;
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
        database.personDao().insertPeople(person);
    }

    @Override
    protected void updateCommand(Person person) {
        database.personDao().updatePeople(person);
    }

    @Override
    protected void deleteCommand() {
        database.personDao().deletePeople(item);
    }
}
