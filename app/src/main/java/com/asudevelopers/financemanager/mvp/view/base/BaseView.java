package com.asudevelopers.financemanager.mvp.view.base;

import com.arellomobile.mvp.MvpView;

public interface BaseView extends MvpView {

    void showMessage(int message);

    void showError(Throwable throwable);
}
