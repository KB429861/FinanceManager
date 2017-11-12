package com.asudevelopers.financemanager.mvp.view;

import com.asudevelopers.financemanager.mvp.model.entity.Person;
import com.asudevelopers.financemanager.mvp.view.base.BaseView;

import java.util.List;

public interface PeopleView extends BaseView {

    void showPeople(List<Person> people);
}
