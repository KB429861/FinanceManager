package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.Person;
import com.asudevelopers.financemanager.mvp.view.PersonView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;

@InjectViewState
public class PersonPresenter extends MvpPresenter<PersonView> {

    private AppDatabase database;

    public PersonPresenter(AppDatabase database) {
        this.database = database;
    }

    public void onClickTest() {
        database.personDao().selectPeople()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new DefaultSubscriber<List<Person>>() {
                    @Override
                    public void onNext(List<Person> people) {
                        Person person;
                        if (people.size() > 0) {
                            person = people.get(0);
                        } else {
                            person = new Person("Empty", "Person");
                        }
                        getViewState().showPerson(person);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        getViewState().showError(throwable);
                    }

                    @Override
                    public void onComplete() {
                        getViewState().showComplete();
                    }
                });
    }
}
