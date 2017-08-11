package com.asudevelopers.financemanager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.asudevelopers.financemanager.database.model.Person;
import com.asudevelopers.financemanager.databinding.ContentPersonBinding;

public class PersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePerson();
            }
        });

        ContentPersonBinding binding = DataBindingUtil.setContentView(this, R.layout.content_person);
        Person person = new Person("John", "555");
        binding.setPerson(person);
    }

    private void savePerson() {


    }
}
