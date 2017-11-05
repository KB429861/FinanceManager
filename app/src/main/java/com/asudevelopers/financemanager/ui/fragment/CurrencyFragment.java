package com.asudevelopers.financemanager.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.base.BaseFragment;

import butterknife.ButterKnife;

public class CurrencyFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
