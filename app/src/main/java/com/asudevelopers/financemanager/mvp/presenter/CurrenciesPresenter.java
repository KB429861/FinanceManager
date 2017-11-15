package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.Currency;
import com.asudevelopers.financemanager.mvp.presenter.base.ItemsPresenter;
import com.asudevelopers.financemanager.mvp.view.CurrenciesView;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class CurrenciesPresenter extends ItemsPresenter<CurrenciesView, Currency> {

    public CurrenciesPresenter(AppDatabase database) {
        super(database);
    }

    @Override
    protected void showItems() {
        database.currencies().select()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        currencies -> {
                            setItems(currencies);
                            getViewState().showCurrencies(currencies);
                        },
                        throwable -> getViewState().showError(throwable)
                );
    }


    public int getItemPosition(String charCode) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getCharCode().equals(charCode)) {
                return i;
            }
        }
        return 0;
    }

    private void insertCurrency(final Currency currency) {
        Completable.fromAction(
                () -> database.currencies().insert(currency))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(
                        throwable -> getViewState().showError(throwable)
                )
                .subscribe();
    }

    private void updateCurrency(final Currency currency) {
        Completable.fromAction(
                () -> database.currencies().update(currency))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(
                        throwable -> getViewState().showError(throwable)
                )
                .subscribe();
    }

    public void updateCurrencies(String[] charCodes, String[] names) {
        for (int i = 0; i < charCodes.length; i++) {
            final String charCode = charCodes[i];
            final String name = names[i];
            database.currencies().select(charCode)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSuccess(
                            currency -> {
                                currency.setName(name);
                                updateCurrency(currency);
                            }
                    )
                    .doOnComplete(
                            () -> {
                                Currency currency = new Currency();
                                currency.setCharCode(charCode);
                                currency.setName(name);
                                insertCurrency(currency);
                            }
                    )
                    .doOnError(
                            throwable -> getViewState().showError(throwable)
                    )
                    .subscribe();
        }
    }
}
