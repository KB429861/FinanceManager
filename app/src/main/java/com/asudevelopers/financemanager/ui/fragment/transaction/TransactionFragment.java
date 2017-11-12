package com.asudevelopers.financemanager.ui.fragment.transaction;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.base.BaseFragment;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.account.Account;
import com.asudevelopers.financemanager.mvp.model.entity.currency.Currency;
import com.asudevelopers.financemanager.mvp.presenter.AccountsPresenter;
import com.asudevelopers.financemanager.mvp.presenter.CurrenciesPresenter;
import com.asudevelopers.financemanager.mvp.presenter.DateTimePresenter;
import com.asudevelopers.financemanager.mvp.view.AccountsView;
import com.asudevelopers.financemanager.mvp.view.CurrenciesView;
import com.asudevelopers.financemanager.mvp.view.DateTimeView;
import com.asudevelopers.financemanager.ui.activity.AccountActivity;
import com.asudevelopers.financemanager.util.adapter.AccountSpinnerAdapter;
import com.asudevelopers.financemanager.util.adapter.CurrencySpinnerAdapter;
import com.asudevelopers.financemanager.util.validation.TextValidator;
import com.asudevelopers.financemanager.util.validation.Validation;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class TransactionFragment extends BaseFragment
        implements AccountsView, DateTimeView, CurrenciesView {

    @BindView(R.id.spn_account)
    protected Spinner accountSpinner;

    @BindView(R.id.spn_currency)
    protected Spinner currencySpinner;

    @BindView(R.id.btn_date)
    protected Button dateButton;

    @BindView(R.id.btn_time)
    protected Button timeButton;

    @BindView(R.id.et_amount)
    protected EditText amountEditText;

    @BindView(R.id.et_description)
    protected EditText descriptionEditText;

    @InjectPresenter
    AccountsPresenter accountsPresenter;

    @InjectPresenter
    DateTimePresenter dateTimePresenter;

    @InjectPresenter
    CurrenciesPresenter currenciesPresenter;

    @ProvidePresenter
    AccountsPresenter provideAccountsPresenter() {
        return new AccountsPresenter(AppDatabase.getInstance(getContext()));
    }

    @ProvidePresenter
    CurrenciesPresenter provideCurrenciesPresenter() {
        return new CurrenciesPresenter(AppDatabase.getInstance(getContext()));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        amountEditText.addTextChangedListener(new TextValidator(amountEditText) {
            @Override
            public void validate(EditText editText) {
                Validation.isAmount(editText, getString(R.string.msg_invalid_amount));
            }
        });
    }

    @Override
    public void showAccounts(List<Account> accounts) {
        AccountSpinnerAdapter adapter = new AccountSpinnerAdapter(getContext(), accounts);
        accountSpinner.setAdapter(adapter);
    }

    @Override
    public void showCurrencies(List<Currency> currencies) {
        CurrencySpinnerAdapter adapter = new CurrencySpinnerAdapter(getContext(), currencies);
        currencySpinner.setAdapter(adapter);
    }

    @OnClick(R.id.btn_account)
    public void createAccount() {
        Intent intent = new Intent(getContext(), AccountActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_date)
    public void showDatePicker() {
        dateTimePresenter.showDatePicker();
    }

    @OnClick(R.id.btn_time)
    public void showTimePicker() {
        dateTimePresenter.showTimePicker();
    }

    @Override
    public void showDate(Calendar calendar) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        dateButton.setText(dateFormat.format(calendar.getTime()));
    }

    @Override
    public void showTime(Calendar calendar) {
        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
        timeButton.setText(dateFormat.format(calendar.getTime()));
    }

    @Override
    public void showDatePicker(Calendar calendar) {
        DatePickerDialog dialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        dateTimePresenter.setDate(year, month, day);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void showTimePicker(Calendar calendar) {
        TimePickerDialog dialog = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {
                        dateTimePresenter.setTime(hour, minute);
                    }
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true);
        dialog.show();
    }

    protected boolean isValid() {
        return Validation.isSelected(accountSpinner, getString(R.string.msg_null_account)) &&
                Validation.isSelected(currencySpinner, getString(R.string.msg_null_currency)) &&
                Validation.isAmount(amountEditText, getString(R.string.msg_invalid_amount));
    }

    public abstract void save();
}
