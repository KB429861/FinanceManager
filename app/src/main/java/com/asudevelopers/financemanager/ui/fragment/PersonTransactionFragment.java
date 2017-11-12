package com.asudevelopers.financemanager.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.Account;
import com.asudevelopers.financemanager.mvp.model.entity.Currency;
import com.asudevelopers.financemanager.mvp.model.entity.Person;
import com.asudevelopers.financemanager.mvp.model.entity.transaction.BorrowTransaction;
import com.asudevelopers.financemanager.mvp.model.entity.transaction.LendTransaction;
import com.asudevelopers.financemanager.mvp.model.entity.transaction.PersonTransaction;
import com.asudevelopers.financemanager.mvp.presenter.PeoplePresenter;
import com.asudevelopers.financemanager.mvp.presenter.PersonTransactionPresenter;
import com.asudevelopers.financemanager.mvp.view.PeopleView;
import com.asudevelopers.financemanager.mvp.view.PersonTransactionView;
import com.asudevelopers.financemanager.ui.activity.PersonActivity;
import com.asudevelopers.financemanager.util.adapter.PersonSpinnerAdapter;
import com.asudevelopers.financemanager.util.validation.Validation;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonTransactionFragment extends TransactionFragment
        implements PeopleView, PersonTransactionView {

    @BindView(R.id.spn_person)
    Spinner personSpinner;

    @BindView(R.id.btn_toggle)
    ToggleButton toggleButton;

    @InjectPresenter
    PeoplePresenter peoplePresenter;
    @InjectPresenter
    PersonTransactionPresenter transactionPresenter;

    @ProvidePresenter
    PeoplePresenter providePeoplePresenter() {
        return new PeoplePresenter(AppDatabase.getInstance(getContext()));
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
        return view;
    }

    @Override
    public void showPeople(List<Person> people) {
        PersonSpinnerAdapter adapter = new PersonSpinnerAdapter(getContext(), people);
        personSpinner.setAdapter(adapter);
    }

    @Override
    public void showTransaction(PersonTransaction transaction) {
        personSpinner.setSelection(
                peoplePresenter.getItemPosition(transaction.getPersonId()));
        accountSpinner.setSelection(
                accountsPresenter.getItemPosition(transaction.getAccountId()));
    }

    @OnClick(R.id.btn_person)
    public void createPerson() {
        Intent intent = new Intent(getContext(), PersonActivity.class);
        startActivity(intent);
    }

    @Override
    protected boolean isValid() {
        return super.isValid() &&
                Validation.isSelected(personSpinner, getString(R.string.msg_null_person));
    }

    @Override
    public void save() {
        if (isValid()) {
            Person person = (Person) personSpinner.getSelectedItem();
            Account account = (Account) accountSpinner.getSelectedItem();
            Date date = dateTimePresenter.getDateTime();
            Currency currency = (Currency) currencySpinner.getSelectedItem();
            double amount = Double.valueOf(amountEditText.getText().toString());
            String description = descriptionEditText.getText().toString();
            boolean isLend = toggleButton.isChecked();
            PersonTransaction transaction;
            if (isLend) {
                transaction = new LendTransaction();
            } else {
                transaction = new BorrowTransaction();
            }
            transaction.setPersonId(person.getId());
            transaction.setAccountId(account.getId());
            transaction.setDate(date);
            transaction.setCurrencyCharCode(currency.getCharCode());
            transaction.setAmount(amount);
            transaction.setDescription(description);
            transactionPresenter.saveItem(transaction);
            getActivity().onBackPressed();
        }
    }
}
