package com.asudevelopers.financemanager.util.adapter.spinner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.asudevelopers.financemanager.mvp.model.entity.base.BaseEntity;

import java.util.List;

public abstract class SpinnerAdapter<E extends BaseEntity> extends ArrayAdapter<E> {

    private int simpleResource;
    private int dropdownResource;

    SpinnerAdapter(@NonNull Context context,
                   int simpleResource,
                   int dropdownResource,
                   @NonNull List<E> objects) {
        super(context, simpleResource, objects);
        this.simpleResource = simpleResource;
        this.dropdownResource = dropdownResource;
    }

    protected abstract void initView(View layout);

    protected abstract void showItem(E item);

    private View getCustomView(int resource, int position, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View layout = inflater.inflate(resource, parent, false);
        initView(layout);
        E item = getItem(position);
        if (item != null) {
            showItem(item);
        }
        return layout;
    }

    @NonNull
    @Override
    public View getView(
            int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(simpleResource, position, parent);
    }

    @Override
    public View getDropDownView(
            int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(dropdownResource, position, parent);
    }
}
