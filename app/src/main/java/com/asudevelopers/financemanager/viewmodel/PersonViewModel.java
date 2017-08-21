package com.asudevelopers.financemanager.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.Bindable;

import com.asudevelopers.financemanager.database.AppDatabase;
import com.asudevelopers.financemanager.database.model.Person;

public class PersonViewModel extends AndroidViewModel {

    private AppDatabase database;

    private Person person;
    private Person personOut;

    public PersonViewModel(Application application) {
        super(application);
        database = AppDatabase.getDatabase(getApplication());
    }

    @Bindable
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        notify();
    }

    @Bindable
    public Person getPersonOut() {
        return personOut;
    }

    public void setPersonOut(Person personOut) {
        this.personOut = personOut;
        notify();
    }

    public void savePerson(Person person) {
        personOut = person;
    }

    public void loadPerson() {

    }


}
