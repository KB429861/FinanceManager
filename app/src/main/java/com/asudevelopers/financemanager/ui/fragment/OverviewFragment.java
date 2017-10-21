package com.asudevelopers.financemanager.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.ui.activity.TransactionActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OverviewFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Overview");
    }

    @OnClick(R.id.btn_create)
    public void onTransactionClick(View view) {
        Intent intent = new Intent(getActivity(), TransactionActivity.class);
        startActivity(intent);
    }
}
