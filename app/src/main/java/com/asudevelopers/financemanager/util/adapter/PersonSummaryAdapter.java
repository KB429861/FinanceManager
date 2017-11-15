package com.asudevelopers.financemanager.util.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.mvp.model.entity.Person;

import java.util.List;

public class PersonSummaryAdapter extends CustomAdapter<Person> {

    private TextView nameTextView;
    private TextView amountTextView;

    public PersonSummaryAdapter(@NonNull Context context, @NonNull List<Person> people) {
        super(context, R.layout.item_person_summary, people);
    }

    @Override
    protected void initView(View layout) {
        nameTextView = layout.findViewById(R.id.tv_name);
        amountTextView = layout.findViewById(R.id.tv_amount);
    }

    @Override
    protected void showItem(Person person) {
        nameTextView.setText(person.getName());
        amountTextView.setText(String.valueOf(person.getAmount()));
    }
}
