package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.person.Person;
import com.asudevelopers.financemanager.mvp.presenter.base.DatabasePresenter;
import com.asudevelopers.financemanager.mvp.view.PersonView;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class PersonPresenter extends DatabasePresenter<PersonView> {

    private Person person = null;

    public PersonPresenter(AppDatabase database) {
        super(database);
    }

    public void savePersonInfo(final String name, final String phone) {
        Completable.fromAction(
                new Action() {
                    @Override
                    public void run() {
                        boolean isUpdate = true;
                        if (person == null) {
                            person = new Person();
                            isUpdate = false;
                        }
                        person.setName(name);
                        person.setPhone(phone);
                        if (isUpdate) {
                            database.personDao().updatePeople(person);
                        } else {
                            database.personDao().insertPeople(person);
                        }
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

    public void loadAndShowPerson(Person person) {
        this.person = person;
        if (person != null) {
            getViewState().showPerson(person);
        }
    }

    public void deletePerson() {
        Completable.fromAction(
                new Action() {
                    @Override
                    public void run() {
                        database.personDao().deletePeople(person);
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

    public boolean isEditMode() {
        return person != null;
    }
}
