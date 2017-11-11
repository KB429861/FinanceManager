package com.asudevelopers.financemanager.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.base.BaseActivity;
import com.asudevelopers.financemanager.ui.fragment.PersonTransactionFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private PersonTransactionFragment currentFragment = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.transaction);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        currentFragment = new PersonTransactionFragment();
        transaction.replace(R.id.content_frame, currentFragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_transaction, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            currentFragment.save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
