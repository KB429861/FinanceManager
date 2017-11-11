package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.person.Person;
import com.asudevelopers.financemanager.mvp.presenter.base.ItemPresenter;
import com.asudevelopers.financemanager.mvp.view.PersonView;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
    protected void insertItem(final Person person) {
        Completable.fromAction(
                new Action() {
                    @Override
                    public void run() {
                        database.personDao().insertPeople(person);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Action() {
                            @Override
                            public void run() {
                                getViewState().showMessage(R.string.msg_saved);
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                getViewState().showError(throwable);
                            }
                        });
    }

    @Override
    protected void updateItem(final Person person) {
        Completable.fromAction(
                new Action() {
                    @Override
                    public void run() {
                        database.personDao().updatePeople(person);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Action() {
                            @Override
                            public void run() {
                                getViewState().showMessage(R.string.msg_saved);
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                getViewState().showError(throwable);
                            }
                        });
    }

    @Override
    public void deleteItem() {
        Completable.fromAction(
                new Action() {
                    @Override
                    public void run() {
                        database.personDao().deletePeople(item);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Action() {
                            @Override
                            public void run() {
                                getViewState().showMessage(R.string.msg_deleted);
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                getViewState().showError(throwable);
                            }
                        });
    }
}
