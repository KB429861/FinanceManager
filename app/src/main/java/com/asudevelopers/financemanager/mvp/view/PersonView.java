package com.asudevelopers.financemanager.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.asudevelopers.financemanager.mvp.model.entity.Person;

public interface PersonView extends MvpView {
    void showPerson(Person person);

    void showError(Throwable throwable);

    void showComplete();
}
