package com.asudevelopers.financemanager.util.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.mvp.model.entity.Account;

import java.util.List;

public class AccountSpinnerAdapter extends CustomAdapter<Account> {

    private TextView nameTextView;

    public AccountSpinnerAdapter(@NonNull Context context, @NonNull List<Account> objects) {
        super(context, R.layout.item_account_spinner, R.layout.item_account_spinner_dropdown, objects);
    }

    @Override
    protected void initView(View layout) {
        nameTextView = layout.findViewById(R.id.tv_name);
    }

    @Override
    protected void showItem(Account item) {
        nameTextView.setText(item.getName());
    }
}
