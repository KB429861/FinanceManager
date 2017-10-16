package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.Person;
import com.asudevelopers.financemanager.mvp.view.PersonView;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class PersonPresenter extends MvpPresenter<PersonView> {

    private AppDatabase database;

    public PersonPresenter(AppDatabase database) {
        this.database = database;
    }

    public void savePerson(Person person) {
        Single.just(person)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<Person>() {
                    @Override
                    public void accept(Person person) throws Exception {
                        database.personDao().insertPeople(person);
                    }
                });
    }

    public void loadPerson(int personId) {
        database.personDao().selectPerson(personId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<Person>() {
                    @Override
                    public void accept(Person person) {
                        try {
                            getViewState().showPerson(person);
                        } catch (Exception e) {
                            getViewState().showError(e);
                        }
                    }
                });
    }
}
