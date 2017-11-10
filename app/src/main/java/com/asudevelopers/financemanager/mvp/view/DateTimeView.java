package com.asudevelopers.financemanager.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.Calendar;

public interface DateTimeView extends MvpView {

    void showDate(Calendar calendar);

    void showTime(Calendar calendar);

    void showDatePicker(Calendar calendar);

    void showTimePicker(Calendar calendar);
}
