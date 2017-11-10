package com.asudevelopers.financemanager.mvp.presenter.base;

import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.base.BaseEntity;
import com.asudevelopers.financemanager.mvp.view.base.BaseView;

import java.util.List;

public abstract class ItemsPresenter<V extends BaseView, E extends BaseEntity>
        extends DatabasePresenter<V> {

    protected List<E> items;

    public ItemsPresenter(AppDatabase database) {
        super(database);
    }

    public abstract void showItems();

    protected void setItems(List<E> items) {
        this.items = items;
    }

    public E getItem(int position) {
        return items.get(position);
    }

    public int getItemPosition(int id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return 0;
    }
}
