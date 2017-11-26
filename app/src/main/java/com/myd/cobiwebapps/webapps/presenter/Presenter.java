package com.myd.cobiwebapps.webapps.presenter;

import android.text.TextUtils;

import com.myd.cobiwebapps.base.BaseModel;
import com.myd.cobiwebapps.contract.WebAppContract;
import com.myd.cobiwebapps.data.source.repository.WebAppRepo;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by MYD on 11/23/17.
 *
 */

public class Presenter<T extends BaseModel> implements WebAppContract.Presenter<T> {

    private CompositeDisposable subscriptions = new CompositeDisposable();
    private WebAppRepo<T> repository;
    private WebAppContract.View<T> view;

    @Inject
    public Presenter(WebAppRepo<T> repository, WebAppContract.View<T> view) {
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
    public void updateData() {
        view.showProgress();
        subscriptions.add(repository.updateInfo()
                .subscribe(
                        x -> {
                            if (x.size() <= 0) {
                                view.showEmptyState();
                            } else {
                                String data = TextUtils.join("\n ", x);
                                view.showData(data);
                            }
                        },
                        e -> view.showError())
        );

    }

    @Override
    public void addData(T model) {
        subscriptions.add(repository.addData(model)
                .subscribe(
                        x -> {
                            if (!x.isEmpty()) {
                                view.showData(x);
                            }
                        }, e -> view.showError()
                ));

    }
}
