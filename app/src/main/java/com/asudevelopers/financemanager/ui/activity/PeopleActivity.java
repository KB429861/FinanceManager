package com.asudevelopers.financemanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.base.BaseActivity;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.Person;
import com.asudevelopers.financemanager.mvp.presenter.PeoplePresenter;
import com.asudevelopers.financemanager.mvp.view.PeopleView;
import com.asudevelopers.financemanager.util.PersonAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PeopleActivity extends BaseActivity implements PeopleView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.lv_people)
    ListView listView;

    @InjectPresenter
    PeoplePresenter peoplePresenter;

    @ProvidePresenter
    PeoplePresenter providePeoplePresenter() {
        return new PeoplePresenter(AppDatabase.getInstance(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.people);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        peoplePresenter.loadPeople();

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Person person = peoplePresenter.getPerson(position);
                        Intent intent = new Intent(getApplicationContext(), PersonActivity.class);
                        intent.putExtra("Person", person);
                        startActivity(intent);
                    }
                });
    }

    @OnClick(R.id.fab)
    public void onCreateClick(View view) {
        Intent intent = new Intent(this, PersonActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void showPeople(List<Person> people) {
        peoplePresenter.setPeople(people);
        PersonAdapter adapter = new PersonAdapter(this, people);
        listView.setAdapter(adapter);
    }
}
