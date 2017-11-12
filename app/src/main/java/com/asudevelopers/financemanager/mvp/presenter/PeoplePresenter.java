package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.Person;
import com.asudevelopers.financemanager.mvp.presenter.base.ItemsPresenter;
import com.asudevelopers.financemanager.mvp.view.PeopleView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class PeoplePresenter extends ItemsPresenter<PeopleView, Person> {

    public PeoplePresenter(AppDatabase database) {
        super(database);
    }

    @Override
    protected void showItems() {
        database.people().select()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<List<Person>>() {
                            @Override
                            public void accept(List<Person> people) {
                                setItems(people);
                                getViewState().showPeople(people);
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                getViewState().showError(throwable);
                            }
                        }
                );
    }
}
