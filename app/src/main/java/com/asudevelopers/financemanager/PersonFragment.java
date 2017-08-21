package com.asudevelopers.financemanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.asudevelopers.financemanager.database.DatabaseHelper;
import com.asudevelopers.financemanager.database.contract.PersonContract;
import com.asudevelopers.financemanager.database.model.PersonModel;

import java.util.List;

public class PersonFragment extends Fragment {

    private DatabaseHelper databaseHelper;
    private EditText nameEditText;
    private EditText phoneEditText;
    private TextView idTextView;
    private TextView nameTextView;
    private TextView phoneTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = DatabaseHelper.getInstance(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        nameEditText = view.findViewById(R.id.et_name);
        phoneEditText = view.findViewById(R.id.et_phone);
        Button saveButton = view.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePerson();
            }
        });
        Button loadButton = view.findViewById(R.id.btn_load);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadPeople();
            }
        });
        idTextView = view.findViewById(R.id.tv_id);
        nameTextView = view.findViewById(R.id.tv_name);
        phoneTextView = view.findViewById(R.id.tv_phone);
        return view;
    }

    private void savePerson() {
        PersonModel person = new PersonModel();
        person.setName(nameEditText.getText().toString());
        person.setPhone(phoneEditText.getText().toString());
        PersonContract.insertPerson(databaseHelper.getWritableDatabase(), person);
    }

    private void loadPeople() {
        List<PersonModel> people = PersonContract.selectPeople(databaseHelper.getReadableDatabase());
        PersonModel person = people.get(0);
        idTextView.setText(String.valueOf(person.getId()));
        nameTextView.setText(person.getName());
        phoneTextView.setText(person.getPhone());
    }
}
