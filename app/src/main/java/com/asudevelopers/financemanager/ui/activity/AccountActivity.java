package com.asudevelopers.financemanager.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.base.BaseActivity;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.account.Account;
import com.asudevelopers.financemanager.mvp.model.entity.currency.Currency;
import com.asudevelopers.financemanager.mvp.presenter.AccountPresenter;
import com.asudevelopers.financemanager.mvp.presenter.CurrenciesPresenter;
import com.asudevelopers.financemanager.mvp.view.AccountView;
import com.asudevelopers.financemanager.mvp.view.CurrenciesView;
import com.asudevelopers.financemanager.util.adapter.CurrencySpinnerAdapter;
import com.asudevelopers.financemanager.util.validation.TextValidator;
import com.asudevelopers.financemanager.util.validation.Validation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountActivity extends BaseActivity implements AccountView, CurrenciesView {

    @BindView(R.id.et_name)
    EditText nameEditText;

    @BindView(R.id.et_balance)
    EditText balanceEditText;

    @BindView(R.id.spn_currency)
    Spinner currencySpinner;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @InjectPresenter
    AccountPresenter accountPresenter;

    @InjectPresenter
    CurrenciesPresenter currenciesPresenter;

    @ProvidePresenter
    AccountPresenter provideAccountPresenter() {
        return new AccountPresenter(AppDatabase.getInstance(this));
    }

    @ProvidePresenter
    CurrenciesPresenter provideCurrenciesPresenter() {
        return new CurrenciesPresenter(AppDatabase.getInstance(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);

        currenciesPresenter.loadCurrencies();

        Account account = (Account) getIntent().getSerializableExtra("Account");
        accountPresenter.loadAndShowAccount(account);

        toolbar.setTitle(R.string.account);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        registerViews();
    }

    private void registerViews() {
        nameEditText.addTextChangedListener(new TextValidator(nameEditText) {
            @Override
            public void validate(EditText editText) {
                Validation.hasText(editText, getString(R.string.msg_empty_field));
            }
        });
        balanceEditText.addTextChangedListener(new TextValidator(balanceEditText) {
            @Override
            public void validate(EditText editText) {
                Validation.isAmount(editText, getString(R.string.msg_invalid_amount));
            }
        });
    }

    public void save() {
        if (isValid()) {
            String name = nameEditText.getText().toString();
            double amount = Double.valueOf(balanceEditText.getText().toString());
            Currency currency = (Currency) currencySpinner.getSelectedItem();
            accountPresenter.saveAccountInfo(name, amount, currency.getCharCode());
            onBackPressed();
        }
    }

    private boolean isValid() {
        return Validation.hasText(nameEditText, getString(R.string.msg_empty_field)) &&
                Validation.isAmount(balanceEditText, getString(R.string.msg_invalid_amount)) &&
                Validation.isSelected(currencySpinner, getString(R.string.msg_null_currency));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_account, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showAccount(Account account) {
        nameEditText.setText(account.getName());
        balanceEditText.setText(String.valueOf(account.getAmount()));
        int position = currenciesPresenter.getCurrencyPosition(account.getCurrencyCharCode());
        currencySpinner.setSelection(position);
    }

    @Override
    public void showCurrencies(List<Currency> currencies) {
        currenciesPresenter.setCurrencies(currencies);
        CurrencySpinnerAdapter adapter = new CurrencySpinnerAdapter(this, currencies);
        currencySpinner.setAdapter(adapter);
    }
}
