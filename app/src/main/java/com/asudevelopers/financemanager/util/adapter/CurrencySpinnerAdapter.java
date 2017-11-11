package com.asudevelopers.financemanager.util.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.mvp.model.entity.currency.Currency;

import java.util.List;

public class CurrencySpinnerAdapter extends CustomAdapter<Currency> {

    private TextView nameTextView;
    private TextView charCodeTextView;

    public CurrencySpinnerAdapter(@NonNull Context context, @NonNull List<Currency> objects) {
        super(context, R.layout.item_currency_spinner, R.layout.item_currency_spinner_dropdown, objects);
    }

    @Override
    protected void initView(View layout) {
        nameTextView = layout.findViewById(R.id.tv_name);
        charCodeTextView = layout.findViewById(R.id.tv_charcode);
    }

    @Override
    protected void showItem(Currency item) {
        nameTextView.setText(item.getName());
        charCodeTextView.setText(item.getCharCode());
    }
}
