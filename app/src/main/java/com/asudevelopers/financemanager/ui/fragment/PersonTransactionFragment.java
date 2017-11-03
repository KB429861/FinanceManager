package com.asudevelopers.financemanager.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.base.BaseFragment;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.account.Account;
import com.asudevelopers.financemanager.mvp.model.entity.person.Person;
import com.asudevelopers.financemanager.mvp.presenter.AccountsPresenter;
import com.asudevelopers.financemanager.mvp.presenter.PeoplePresenter;
import com.asudevelopers.financemanager.mvp.view.AccountsView;
import com.asudevelopers.financemanager.mvp.view.PeopleView;
import com.asudevelopers.financemanager.ui.activity.AccountActivity;
import com.asudevelopers.financemanager.ui.activity.PersonActivity;
import com.asudevelopers.financemanager.util.adapter.AccountSpinnerAdapter;
import com.asudevelopers.financemanager.util.adapter.PersonSpinnerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonTransactionFragment extends BaseFragment implements PeopleView, AccountsView {

    @BindView(R.id.spn_person)
    Spinner personSpinner;

    @BindView(R.id.spn_account)
    Spinner accountSpinner;

    @InjectPresenter
    PeoplePresenter peoplePresenter;
    @InjectPresenter
    AccountsPresenter accountsPresenter;

    @ProvidePresenter
    PeoplePresenter providePeoplePresenter() {
        return new PeoplePresenter(AppDatabase.getInstance(getContext()));
    }

    @ProvidePresenter
    AccountsPresenter provideAccountsPresenter() {
        return new AccountsPresenter(AppDatabase.getInstance(getContext()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_transaction, container, false);
        ButterKnife.bind(this, view);

        peoplePresenter.loadPeople();
        accountsPresenter.loadAccounts();

        return view;
    }

    @Override
    public void showPeople(List<Person> people) {
        peoplePresenter.setPeople(people);
        PersonSpinnerAdapter adapter = new PersonSpinnerAdapter(getContext(), people);
        personSpinner.setAdapter(adapter);
    }

    @Override
    public void showAccounts(List<Account> accounts) {
        accountsPresenter.setAccounts(accounts);
        AccountSpinnerAdapter adapter = new AccountSpinnerAdapter(getContext(), accounts);
        accountSpinner.setAdapter(adapter);
    }

    @OnClick(R.id.btn_person)
    public void OnCreatePersonClick(View view) {
        Intent intent = new Intent(getContext(), PersonActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_account)
    public void OnCreateAccountClick(View view) {
        Intent intent = new Intent(getContext(), AccountActivity.class);
        startActivity(intent);
    }
}
