package com.asudevelopers.financemanager.mvp.presenter.base;

import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.base.BaseEntity;
import com.asudevelopers.financemanager.mvp.view.base.BaseView;

public abstract class ItemPresenter<V extends BaseView, E extends BaseEntity>
        extends DatabasePresenter<V> {

    protected E item = null;

    public ItemPresenter(AppDatabase database) {
        super(database);
    }

    public boolean isItemNull() {
        return item == null;
    }

    public void setItem(E item) {
        this.item = item;
        if (item != null) {
            showItem(item);
        }
    }

    protected abstract void showItem(E item);

    public void saveItem(E item) {
        if (this.item == null) {
            insertItem(item);
        } else {
            item.setId(this.item.getId());
            updateItem(item);
        }
    }

    protected abstract void insertItem(E item);

    protected abstract void updateItem(E item);

    public abstract void deleteItem();
}
