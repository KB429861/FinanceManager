package com.asudevelopers.financemanager;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.asudevelopers.financemanager.databinding.ContentPersonBinding;
import com.asudevelopers.financemanager.viewmodel.PersonViewModel;

public class PersonActivity extends LifecycleActivity {

    private PersonViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        viewModel = ViewModelProviders.of(this).get(PersonViewModel.class);
        ContentPersonBinding binding = DataBindingUtil.setContentView(this, R.layout.content_person);
        binding.setViewModel(viewModel);
    }
}
