package com.asudevelopers.financemanager.util.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.mvp.model.entity.currency.Currency;

import java.util.List;

public class CurrencyListAdapter extends ArrayAdapter<Currency> {

    public CurrencyListAdapter(@NonNull Context context, @NonNull List<Currency> currencies) {
        super(context, R.layout.item_currency_list, currencies);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Currency currency = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null || convertView.getTag() == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_currency_list, parent, false);
            viewHolder.nameTextView = convertView.findViewById(R.id.tv_name);
            viewHolder.charCodeTextView = convertView.findViewById(R.id.tv_charcode);
            viewHolder.valueTextView = convertView.findViewById(R.id.tv_value);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (currency != null) {
            viewHolder.nameTextView.setText(currency.getName());
            viewHolder.charCodeTextView.setText(currency.getCharCode());
            viewHolder.valueTextView.setText(String.valueOf(currency.getValue()));
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView nameTextView;
        TextView charCodeTextView;
        TextView valueTextView;
    }
}
