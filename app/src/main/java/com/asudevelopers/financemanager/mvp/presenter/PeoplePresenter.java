package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.asudevelopers.financemanager.base.BasePresenter;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.person.Person;
import com.asudevelopers.financemanager.mvp.view.PeopleView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class PeoplePresenter extends BasePresenter<PeopleView> {

    private List<Person> people;

    public PeoplePresenter(AppDatabase database) {
        super(database);
    }

    public void loadPeople() {
        database.personDao().selectPeople()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<List<Person>>() {
                            @Override
                            public void accept(List<Person> people) {
                                getViewState().showPeople(people);
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                getViewState().showError(throwable);
                            }
                        });
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public Person getPerson(int position) {
        return people.get(position);
    }
}
