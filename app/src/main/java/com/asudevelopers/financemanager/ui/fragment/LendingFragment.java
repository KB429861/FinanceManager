package com.asudevelopers.financemanager.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.mvp.model.entity.Person;
import com.asudevelopers.financemanager.mvp.view.PeopleView;
import com.asudevelopers.financemanager.util.adapter.PersonSummaryAdapter;

import java.util.List;

import butterknife.BindView;

public class LendingFragment extends BaseFragment implements PeopleView {

    @BindView(R.id.lv_people)
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lending, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.lending);
    }

    @Override
    public void showPeople(List<Person> people) {
        PersonSummaryAdapter adapter = new PersonSummaryAdapter(getContext(), people);
        listView.setAdapter(adapter);
    }
}
