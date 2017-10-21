package com.asudevelopers.financemanager.mvp.view;

import com.asudevelopers.financemanager.base.BaseView;
import com.asudevelopers.financemanager.mvp.model.entity.Person;

import java.util.List;

public interface PeopleView extends BaseView {

    void showPeople(List<Person> people);
}
