package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.Person;
import com.asudevelopers.financemanager.mvp.view.PeopleView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;

@InjectViewState
public class PeoplePresenter extends MvpPresenter<PeopleView> {

    private AppDatabase database;

    public PeoplePresenter(AppDatabase database) {
        this.database = database;
    }

    public void loadPeople() {
        database.personDao().selectPeople()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new DefaultSubscriber<List<Person>>() {
                    @Override
                    public void onNext(List<Person> people) {
                        getViewState().showPeople(people);
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
