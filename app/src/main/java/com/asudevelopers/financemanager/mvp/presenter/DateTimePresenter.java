package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.asudevelopers.financemanager.mvp.view.DateTimeView;

import java.util.Calendar;

@InjectViewState
public class DateTimePresenter extends MvpPresenter<DateTimeView> {

    private Calendar calendar;

    public DateTimePresenter() {
        calendar = Calendar.getInstance();
        getViewState().showDate(calendar);
        getViewState().showTime(calendar);
    }

    public void setDate(int year, int month, int day) {
        calendar.set(year, month, day);
        getViewState().showDate(calendar);
    }

    public void setTime(int hour, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        getViewState().showTime(calendar);
    }

    public void showDatePicker() {
        getViewState().showDatePicker(calendar);
    }

    public void showTimePicker() {
        getViewState().showTimePicker(calendar);
    }
}
