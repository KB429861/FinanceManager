package com.asudevelopers.financemanager.ui.fragment;

import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.asudevelopers.financemanager.mvp.view.base.BaseView;

public abstract class BaseFragment extends MvpAppCompatFragment implements BaseView {

    @Override
    public void showMessage(int message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(getContext(), throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }
}
