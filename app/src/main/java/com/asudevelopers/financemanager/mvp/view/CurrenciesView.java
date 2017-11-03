package com.asudevelopers.financemanager.mvp.view;

import com.asudevelopers.financemanager.base.BaseView;
import com.asudevelopers.financemanager.mvp.model.entity.currency.Currency;

import java.util.List;

public interface CurrenciesView extends BaseView {

    void showCurrencies(List<Currency> currencies);
}
