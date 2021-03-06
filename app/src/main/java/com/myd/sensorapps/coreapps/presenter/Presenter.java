package com.myd.sensorapps.coreapps.presenter;

import com.myd.sensorapps.base.BaseModel;
import com.myd.sensorapps.contract.AppContract;
import com.myd.sensorapps.data.source.repository.AppRepo;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by MYD on 11/23/17.
 *
 */

public class Presenter<T extends BaseModel> implements AppContract.Presenter<T> {

    private CompositeDisposable subscriptions = new CompositeDisposable();
    private AppRepo<T> repository;
    private AppContract.View<T> view;

    @Inject
    public Presenter(AppRepo<T> repository, AppContract.View<T> view) {
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        if (subscriptions != null && !subscriptions.isDisposed()) {
            subscriptions.dispose();
        }
    }

    @Override
    public void fetchAndUpdate() {
        view.showProgress();
        subscriptions.add(repository.updateInfo()
                .subscribe(
                        x -> {
                            if (x.size() <= 0) {
                                view.showEmptyState();
                            } else {
                                for (T model : x) {
                                    view.showData(model);
                                }
                            }
                        },
                        e -> view.showError())
        );

    }

    @Override
    public void addDataAndUpdate(T model) {
        subscriptions.add(repository.addData(model)
                .subscribe(
                        x -> view.showData(x),
                        e -> view.showError()
                ));

    }

    @Override
    public void getDataAndUpdate() {
        subscriptions.add(repository.getSingleData()
                .subscribe(
                        x -> view.showData(x),
                        e -> view.showError()
                ));
    }
}
