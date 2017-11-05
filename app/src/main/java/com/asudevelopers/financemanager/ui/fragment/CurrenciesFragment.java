package com.asudevelopers.financemanager.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.base.BaseFragment;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.currency.Currency;
import com.asudevelopers.financemanager.mvp.presenter.CurrenciesPresenter;
import com.asudevelopers.financemanager.mvp.view.CurrenciesView;
import com.asudevelopers.financemanager.util.adapter.CurrencyListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrenciesFragment extends BaseFragment implements CurrenciesView {

    @BindView(R.id.lv_currencies)
    ListView listView;

    @InjectPresenter
    CurrenciesPresenter currenciesPresenter;

    @ProvidePresenter
    CurrenciesPresenter provideCurrenciesPresenter() {
        return new CurrenciesPresenter(AppDatabase.getInstance(getContext()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currencies, container, false);

        ButterKnife.bind(this, view);

        currenciesPresenter.loadCurrencies();

        return view;
    }

    @Override
    public void showCurrencies(List<Currency> currencies) {
        currenciesPresenter.setCurrencies(currencies);
        CurrencyListAdapter adapter = new CurrencyListAdapter(getContext(), currencies);
        listView.setAdapter(adapter);
    }
}
