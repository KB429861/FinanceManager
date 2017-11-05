package com.asudevelopers.financemanager.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.base.BaseActivity;
import com.asudevelopers.financemanager.ui.fragment.CurrenciesFragment;
import com.asudevelopers.financemanager.ui.fragment.CurrencyFragment;
import com.asudevelopers.financemanager.util.adapter.CurrenciesPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrenciesActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencies);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.currencies);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        CurrenciesPagerAdapter adapter = new CurrenciesPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CurrencyFragment(), getString(R.string.currency));
        adapter.addFragment(new CurrenciesFragment(), getString(R.string.currencies));
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
