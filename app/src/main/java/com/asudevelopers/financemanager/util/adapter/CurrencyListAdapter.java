package com.asudevelopers.financemanager.util.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.mvp.model.entity.Currency;

import java.util.List;

public class CurrencyListAdapter extends CustomAdapter<Currency> {

    private TextView nameTextView;
    private TextView charCodeTextView;
    private TextView valueTextView;

    public CurrencyListAdapter(@NonNull Context context, @NonNull List<Currency> currencies) {
        super(context, R.layout.item_currency_list, currencies);
    }

    @Override
    protected void initView(View layout) {
        nameTextView = layout.findViewById(R.id.tv_name);
        charCodeTextView = layout.findViewById(R.id.tv_charcode);
        valueTextView = layout.findViewById(R.id.tv_value);
    }

    @Override
    protected void showItem(Currency currency) {
        nameTextView.setText(currency.getName());
        charCodeTextView.setText(currency.getCharCode());
        valueTextView.setText(String.valueOf(currency.getValue()));
    }
}
