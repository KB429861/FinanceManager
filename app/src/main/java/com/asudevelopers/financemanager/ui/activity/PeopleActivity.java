package com.asudevelopers.financemanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.Person;
import com.asudevelopers.financemanager.mvp.presenter.PeoplePresenter;
import com.asudevelopers.financemanager.mvp.view.PeopleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class PeopleActivity extends MvpAppCompatActivity implements PeopleView {

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
    }

    @OnClick(R.id.fab)
    public void onCreateClick(View view) {
        Intent intent = new Intent(this, PersonActivity.class);
        startActivity(intent);
    }

    @OnItemClick(R.id.lv_people)
    public void onPersonClick(int position) {
        Toast.makeText(this, String.valueOf(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void showPeople(List<Person> people) {
        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        HashMap<String, String> map;
        for (Person person : people) {
            map = new HashMap<>();
            map.put("Name", person.getName());
            map.put("Phone", person.getPhone());
            data.add(map);
        }
        String[] from = {"Name", "Phone"};
        int[] to = {R.id.tv_name, R.id.tv_phone};
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.fragment_person_item, from, to);
        listView.setAdapter(adapter);
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showComplete() {
        Toast.makeText(this, "Complete!", Toast.LENGTH_LONG).show();
    }
}
