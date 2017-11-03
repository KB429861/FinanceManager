package com.asudevelopers.financemanager.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.asudevelopers.financemanager.R;
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

    public void saveCurrency(final Currency currency) {
        Completable.fromAction(
                new Action() {
                    @Override
                    public void run() {
                        database.currencyDao().insertCurrencies(currency);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Action() {
                            @Override
                            public void run() {
                                getViewState().showMessage(R.string.msg_saved);
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                getViewState().showError(throwable);
                            }
                        });
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
                        });
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

    public Currency getCurrency(int position) {
        return currencies.get(position);
    }
}
