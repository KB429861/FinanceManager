package com.asudevelopers.financemanager.ui.fragment;

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
import com.asudevelopers.financemanager.mvp.model.entity.person.Person;
import com.asudevelopers.financemanager.mvp.presenter.PeoplePresenter;
import com.asudevelopers.financemanager.mvp.view.PeopleView;
import com.asudevelopers.financemanager.util.adapter.PersonSpinnerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonTransactionFragment extends BaseFragment implements PeopleView {

    @BindView(R.id.spn_person)
    Spinner personSpinner;

    @InjectPresenter
    PeoplePresenter peoplePresenter;

    @ProvidePresenter
    PeoplePresenter providePeoplePresenter() {
        return new PeoplePresenter(AppDatabase.getInstance(getContext()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_transaction, container, false);
        ButterKnife.bind(this, view);

        peoplePresenter.loadPeople();

        return view;
    }

    @Override
    public void showPeople(List<Person> people) {
        peoplePresenter.setPeople(people);
        PersonSpinnerAdapter adapter = new PersonSpinnerAdapter(getContext(), people);
        personSpinner.setAdapter(adapter);
    }
}
