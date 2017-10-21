package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.base.BasePresenter;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.Person;
import com.asudevelopers.financemanager.mvp.view.PersonView;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class PersonPresenter extends BasePresenter<PersonView> {

    private Person person;

    public PersonPresenter(AppDatabase database) {
        super(database);
    }

    public void savePersonInfo(final String name, final String phone) {
        Completable.fromAction(
                new Action() {
                    @Override
                    public void run() {
                        person.setName(name);
                        person.setPhone(phone);
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

    public void loadAndShowPerson(Person person) {
        if (person == null) {
            this.person = new Person();
        } else {
            this.person = person;
        }
        getViewState().showPerson(this.person);
    }
}
