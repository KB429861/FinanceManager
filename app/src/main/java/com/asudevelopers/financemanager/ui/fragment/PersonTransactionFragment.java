package com.asudevelopers.financemanager.ui.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.base.BaseFragment;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.account.Account;
import com.asudevelopers.financemanager.mvp.model.entity.currency.Currency;
import com.asudevelopers.financemanager.mvp.model.entity.person.Person;
import com.asudevelopers.financemanager.mvp.model.entity.transaction.PersonTransaction;
import com.asudevelopers.financemanager.mvp.presenter.AccountsPresenter;
import com.asudevelopers.financemanager.mvp.presenter.CurrenciesPresenter;
import com.asudevelopers.financemanager.mvp.presenter.DateTimePresenter;
import com.asudevelopers.financemanager.mvp.presenter.PeoplePresenter;
import com.asudevelopers.financemanager.mvp.presenter.PersonTransactionPresenter;
import com.asudevelopers.financemanager.mvp.view.AccountsView;
import com.asudevelopers.financemanager.mvp.view.CurrenciesView;
import com.asudevelopers.financemanager.mvp.view.DateTimeView;
import com.asudevelopers.financemanager.mvp.view.PeopleView;
import com.asudevelopers.financemanager.mvp.view.PersonTransactionView;
import com.asudevelopers.financemanager.ui.activity.AccountActivity;
import com.asudevelopers.financemanager.ui.activity.PersonActivity;
import com.asudevelopers.financemanager.util.adapter.spinner.AccountSpinnerAdapter;
import com.asudevelopers.financemanager.util.adapter.spinner.CurrencySpinnerAdapter;
import com.asudevelopers.financemanager.util.adapter.spinner.PersonSpinnerAdapter;
import com.asudevelopers.financemanager.util.validation.Validation;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonTransactionFragment extends BaseFragment
        implements PeopleView, AccountsView, DateTimeView, CurrenciesView, PersonTransactionView {

    @BindView(R.id.spn_person)
    Spinner personSpinner;

    @BindView(R.id.spn_account)
    Spinner accountSpinner;

    @BindView(R.id.spn_currency)
    Spinner currencySpinner;

    @BindView(R.id.btn_date)
    Button dateButton;

    @BindView(R.id.btn_time)
    Button timeButton;

    @BindView(R.id.btn_toggle)
    ToggleButton toggleButton;

    @BindView(R.id.et_amount)
    EditText amountEditText;

    @BindView(R.id.et_description)
    EditText descriptionEditText;

    @InjectPresenter
    PeoplePresenter peoplePresenter;

    @InjectPresenter
    AccountsPresenter accountsPresenter;

    @InjectPresenter
    DateTimePresenter dateTimePresenter;

    @InjectPresenter
    CurrenciesPresenter currenciesPresenter;

    @InjectPresenter
    PersonTransactionPresenter transactionPresenter;

    @ProvidePresenter
    PeoplePresenter providePeoplePresenter() {
        return new PeoplePresenter(AppDatabase.getInstance(getContext()));
    }

    @ProvidePresenter
    AccountsPresenter provideAccountsPresenter() {
        return new AccountsPresenter(AppDatabase.getInstance(getContext()));
    }

    @ProvidePresenter
    CurrenciesPresenter provideCurrenciesPresenter() {
        return new CurrenciesPresenter(AppDatabase.getInstance(getContext()));
    }

    @ProvidePresenter
    PersonTransactionPresenter provideTransactionPresenter() {
        return new PersonTransactionPresenter(AppDatabase.getInstance(getContext()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_person_transaction, container, false);
        ButterKnife.bind(this, view);

        peoplePresenter.showItems();
        accountsPresenter.showItems();
        currenciesPresenter.showItems();

        dateTimePresenter.onCreateView();

        return view;
    }

    @Override
    public void showPeople(List<Person> people) {
        PersonSpinnerAdapter adapter = new PersonSpinnerAdapter(getContext(), people);
        personSpinner.setAdapter(adapter);
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

    @Override
    public void showTransaction(PersonTransaction transaction) {
        personSpinner.setSelection(
                peoplePresenter.getItemPosition(transaction.getPersonId()));
        accountSpinner.setSelection(
                accountsPresenter.getItemPosition(transaction.getAccountId()));
    }

    @OnClick(R.id.btn_person)
    public void createPerson(View view) {
        Intent intent = new Intent(getContext(), PersonActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_account)
    public void createAccount(View view) {
        Intent intent = new Intent(getContext(), AccountActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_date)
    public void showDatePicker(View view) {
        dateTimePresenter.showDatePicker();
    }

    @OnClick(R.id.btn_time)
    public void showTimePicker(View view) {
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

    public void save() {
        if (isValid()) {

        }
    }

    private boolean isValid() {
        return Validation.isSelected(personSpinner, getString(R.string.msg_null_person)) &&
                Validation.isSelected(accountSpinner, getString(R.string.msg_null_account)) &&
                Validation.isSelected(currencySpinner, getString(R.string.msg_null_currency)) &&
                Validation.isAmount(amountEditText, getString(R.string.msg_invalid_amount));
    }
}
