package com.asudevelopers.financemanager.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.asudevelopers.financemanager.database.AppDatabase;

public class PeopleViewModel extends AndroidViewModel {

    private AppDatabase database;

    public PeopleViewModel(Application application) {
        super(application);
        database = AppDatabase.getDatabase(this.getApplication());
    }
}
