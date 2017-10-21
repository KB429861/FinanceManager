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

public class PersonListAdapter extends ArrayAdapter<Person> {

    public PersonListAdapter(@NonNull Context context, @NonNull List<Person> objects) {
        super(context, R.layout.item_person_list, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Person person = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null || convertView.getTag() == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_person_list, parent, false);
            viewHolder.nameTextView = convertView.findViewById(R.id.tv_name);
            viewHolder.phoneTextView = convertView.findViewById(R.id.tv_phone);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (person != null) {
            viewHolder.nameTextView.setText(person.getName());
            viewHolder.phoneTextView.setText(person.getPhone());
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView nameTextView;
        TextView phoneTextView;
    }
}
