package com.asudevelopers.financemanager.mvp.view;

import com.asudevelopers.financemanager.base.BaseView;
import com.asudevelopers.financemanager.mvp.model.entity.person.Person;

public interface PersonView extends BaseView {

    void showPerson(Person person);

    void showPersonInfo(String name, String phone);
}
