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
import com.asudevelopers.financemanager.mvp.model.entity.account.Account;

import java.util.List;

public class AccountSpinnerAdapter extends ArrayAdapter<Account> {

    public AccountSpinnerAdapter(@NonNull Context context, @NonNull List<Account> accounts) {
        super(context, R.layout.item_account_spinner, accounts);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Account account = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null || convertView.getTag() == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_account_spinner, parent, false);
            viewHolder.nameTextView = convertView.findViewById(R.id.tv_name);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (account != null) {
            viewHolder.nameTextView.setText(account.getName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Account account = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null || convertView.getTag() == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_account_spinner_dropdown, parent, false);
            viewHolder.nameTextView = convertView.findViewById(R.id.tv_name);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (account != null) {
            viewHolder.nameTextView.setText(account.getName());
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView nameTextView;
    }
}
