package com.asudevelopers.financemanager.util.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.mvp.model.entity.person.Person;

import java.util.List;

public class PersonSpinnerAdapter extends ArrayAdapter<Person> {

    public PersonSpinnerAdapter(@NonNull Context context, @NonNull List<Person> objects) {
        super(context, R.layout.item_person_spinner, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Person person = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null || convertView.getTag() == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_person_spinner, parent, false);
            viewHolder.nameTextView = convertView.findViewById(R.id.tv_name);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (person != null) {
            viewHolder.nameTextView.setText(person.getName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Person person = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null || convertView.getTag() == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_person_spinner_dropdown, parent, false);
            viewHolder.nameTextView = convertView.findViewById(R.id.tv_name);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (person != null) {
            viewHolder.nameTextView.setText(person.getName());
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView nameTextView;
    }
}
