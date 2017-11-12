package com.asudevelopers.financemanager.mvp.view;

import com.asudevelopers.financemanager.mvp.model.entity.Currency;
import com.asudevelopers.financemanager.mvp.view.base.BaseView;

import java.util.List;

public interface CurrenciesView extends BaseView {

    void showCurrencies(List<Currency> currencies);
}
