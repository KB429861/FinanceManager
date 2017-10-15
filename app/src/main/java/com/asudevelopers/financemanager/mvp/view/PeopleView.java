package com.asudevelopers.financemanager.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.asudevelopers.financemanager.mvp.model.entity.Person;

import java.util.List;

public interface PeopleView extends MvpView {

    void showPeople(List<Person> people);

    void showError(Throwable throwable);

    void showComplete();
}
