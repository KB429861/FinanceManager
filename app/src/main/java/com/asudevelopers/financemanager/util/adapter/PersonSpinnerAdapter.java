package com.asudevelopers.financemanager.util.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.mvp.model.entity.Person;

import java.util.List;

public class PersonSpinnerAdapter extends CustomAdapter<Person> {

    private TextView nameTextView;

    public PersonSpinnerAdapter(@NonNull Context context, @NonNull List<Person> objects) {
        super(context, R.layout.item_person_spinner, R.layout.item_person_spinner_dropdown, objects);
    }

    @Override
    protected void initView(View layout) {
        nameTextView = layout.findViewById(R.id.tv_name);
    }

    @Override
    protected void showItem(Person item) {
        nameTextView.setText(item.getName());
    }
}
