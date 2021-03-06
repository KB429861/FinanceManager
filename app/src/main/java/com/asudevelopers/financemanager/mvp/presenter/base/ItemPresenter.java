package com.asudevelopers.financemanager.mvp.presenter.base;

import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.base.BaseEntity;
import com.asudevelopers.financemanager.mvp.view.base.BaseView;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

    private void insertItem(final E item) {
        Completable.fromAction(
                () -> insertCommand(item))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onInsertCompleted,
                        this::onInsertError);
    }

    private void updateItem(final E item) {
        Completable.fromAction(
                () -> updateCommand(item))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onUpdateCompleted,
                        this::onUpdateError);
    }

    public void deleteItem() {
        Completable.fromAction(
                () -> deleteCommand(item))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onDeleteCompleted,
                        this::onDeleteError);
    }

    protected abstract void insertCommand(E item);

    private void onInsertCompleted() {
        getViewState().showMessage(R.string.msg_saved);
    }

    private void onInsertError(Throwable throwable) {
        getViewState().showError(throwable);
    }

    protected abstract void updateCommand(E item);

    private void onUpdateCompleted() {
        getViewState().showMessage(R.string.msg_saved);
    }

    private void onUpdateError(Throwable throwable) {
        getViewState().showError(throwable);
    }

    protected abstract void deleteCommand(E item);

    private void onDeleteCompleted() {
        getViewState().showMessage(R.string.msg_deleted);
    }

    private void onDeleteError(Throwable throwable) {
        getViewState().showError(throwable);
    }
}
