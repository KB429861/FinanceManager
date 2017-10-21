package com.asudevelopers.financemanager.base;

import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;

public abstract class BaseActivity extends MvpAppCompatActivity implements BaseView {

    @Override
    public void showMessage(int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }
}
