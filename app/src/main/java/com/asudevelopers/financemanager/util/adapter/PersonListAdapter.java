package com.asudevelopers.financemanager.util.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.mvp.model.entity.Person;

import java.util.List;

public class PersonListAdapter extends CustomAdapter<Person> {

    private TextView nameTextView;
    private TextView phoneTextView;

    public PersonListAdapter(@NonNull Context context, @NonNull List<Person> people) {
        super(context, R.layout.item_person_list, people);
    }

    @Override
    protected void initView(View layout) {
        nameTextView = layout.findViewById(R.id.tv_name);
        phoneTextView = layout.findViewById(R.id.tv_phone);
    }

    @Override
    protected void showItem(Person person) {
        nameTextView.setText(person.getName());
        phoneTextView.setText(person.getPhone());
    }
}
