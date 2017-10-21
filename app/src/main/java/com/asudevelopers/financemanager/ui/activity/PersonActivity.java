package com.asudevelopers.financemanager.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.base.BaseActivity;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.Person;
import com.asudevelopers.financemanager.mvp.presenter.PersonPresenter;
import com.asudevelopers.financemanager.mvp.view.PersonView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonActivity extends BaseActivity implements PersonView {

    @BindView(R.id.et_name)
    EditText nameEditText;

    @BindView(R.id.et_phone)
    EditText phoneEditText;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @InjectPresenter
    PersonPresenter personPresenter;

    @ProvidePresenter
    PersonPresenter providePersonPresenter() {
        return new PersonPresenter(AppDatabase.getInstance(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);

        Person person = (Person) getIntent().getSerializableExtra("Person");
        personPresenter.loadAndShowPerson(person);

        toolbar.setTitle(R.string.person);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @OnClick(R.id.fab)
    public void onSaveClick(View view) {
        String name = nameEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        personPresenter.savePersonInfo(name, phone);
        onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void showPerson(Person person) {
        nameEditText.setText(person.getName());
        phoneEditText.setText(person.getPhone());
    }
}
