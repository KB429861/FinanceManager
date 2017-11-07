package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.asudevelopers.financemanager.base.BasePresenter;
import com.asudevelopers.financemanager.mvp.model.common.AppDatabase;
import com.asudevelopers.financemanager.mvp.model.entity.currency.Currency;
import com.asudevelopers.financemanager.mvp.view.CurrenciesView;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class CurrenciesPresenter extends BasePresenter<CurrenciesView> {

    private List<Currency> currencies;

    public CurrenciesPresenter(AppDatabase database) {
        super(database);
    }

    public void loadCurrencies() {
        database.currencyDao().selectCurrencies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<List<Currency>>() {
                            @Override
                            public void accept(List<Currency> currencies) {
                                getViewState().showCurrencies(currencies);
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                getViewState().showError(throwable);
                            }
                        }
                );
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public int getCurrencyPosition(int id) {
        for (int i = 0; i < currencies.size(); i++) {
            if (currencies.get(i).getId() == id) {
                return i;
            }
        }
        return 0;
    }

    private void insertCurrency(final Currency currency) {
        Completable.fromAction(
                new Action() {
                    @Override
                    public void run() {
                        database.currencyDao().insertCurrencies(currency);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                getViewState().showError(throwable);
                            }
                        }
                )
                .subscribe();
    }

    private void updateCurrency(final Currency currency) {
        Completable.fromAction(
                new Action() {
                    @Override
                    public void run() {
                        database.currencyDao().updateCurrencies(currency);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                getViewState().showError(throwable);
                            }
                        }
                )
                .subscribe();
    }

    public void updateCurrencies(String[] charCodes, String[] names) {
        for (int i = 0; i < charCodes.length; i++) {
            final String charCode = charCodes[i];
            final String name = names[i];
            database.currencyDao().selectCurrency(charCode)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSuccess(
                            new Consumer<Currency>() {
                                @Override
                                public void accept(Currency currency) {
                                    currency.setName(name);
                                    updateCurrency(currency);
                                }
                            }
                    )
                    .doOnComplete(
                            new Action() {
                                @Override
                                public void run() {
                                    Currency currency = new Currency();
                                    currency.setCharCode(charCode);
                                    currency.setName(name);
                                    insertCurrency(currency);
                                }
                            }
                    )
                    .doOnError(
                            new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) {
                                    getViewState().showError(throwable);
                                }
                            }
                    )
                    .subscribe();
        }
    }
}
