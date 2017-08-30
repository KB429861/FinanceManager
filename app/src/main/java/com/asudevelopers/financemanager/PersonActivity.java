package com.asudevelopers.financemanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.asudevelopers.financemanager.database.DatabaseHelper;
import com.asudevelopers.financemanager.database.contract.PersonContract;
import com.asudevelopers.financemanager.database.model.PersonModel;

public class PersonActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText nameEditText;
    private EditText phoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        databaseHelper = DatabaseHelper.getInstance(getApplicationContext());
        nameEditText = findViewById(R.id.et_name);
        phoneEditText = findViewById(R.id.et_phone);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.person);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        FloatingActionButton actionButton = findViewById(R.id.fab);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePerson();
                onBackPressed();
            }
        });
    }

    public void savePerson() {
        PersonModel person = new PersonModel();
        person.setName(nameEditText.getText().toString());
        person.setPhone(phoneEditText.getText().toString());
        PersonContract.insertPerson(databaseHelper.getWritableDatabase(), person);
    }
}
