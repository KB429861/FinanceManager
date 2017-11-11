package com.asudevelopers.financemanager.mvp.presenter.base;

import com.asudevelopers.financemanager.R;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.base.BaseEntity;
import com.asudevelopers.financemanager.mvp.view.base.BaseView;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
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
                new Action() {
                    @Override
                    public void run() {
                        insertCommand(item);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Action() {
                            @Override
                            public void run() {
                                onInsertCompleted();
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                onInsertError(throwable);
                            }
                        });
    }

    private void updateItem(final E item) {
        Completable.fromAction(
                new Action() {
                    @Override
                    public void run() {
                        updateCommand(item);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Action() {
                            @Override
                            public void run() {
                                onUpdateCompleted();
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                onUpdateError(throwable);
                            }
                        });
    }

    public void deleteItem() {
        Completable.fromAction(
                new Action() {
                    @Override
                    public void run() {
                        deleteCommand(item);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Action() {
                            @Override
                            public void run() {
                                onDeleteCompleted();
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                onDeleteError(throwable);
                            }
                        });
    }

    protected abstract void insertCommand(E item);

    protected void onInsertCompleted() {
        getViewState().showMessage(R.string.msg_saved);
    }

    protected void onInsertError(Throwable throwable) {
        getViewState().showError(throwable);
    }

    protected abstract void updateCommand(E item);

    protected void onUpdateCompleted() {
        getViewState().showMessage(R.string.msg_saved);
    }

    protected void onUpdateError(Throwable throwable) {
        getViewState().showError(throwable);
    }

    protected abstract void deleteCommand(E item);

    protected void onDeleteCompleted() {
        getViewState().showMessage(R.string.msg_deleted);
    }

    protected void onDeleteError(Throwable throwable) {
        getViewState().showError(throwable);
    }
}
