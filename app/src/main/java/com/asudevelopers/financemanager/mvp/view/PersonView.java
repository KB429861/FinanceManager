package com.asudevelopers.financemanager.mvp.view;

import com.asudevelopers.financemanager.mvp.model.entity.Person;
import com.asudevelopers.financemanager.mvp.view.base.BaseView;

public interface PersonView extends BaseView {

    void showPerson(Person person);

    void showPersonInfo(String name, String phone);
}
