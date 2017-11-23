package com.myd.cobiwebapps.webapps.presenter;

import android.text.TextUtils;

import com.myd.cobiwebapps.contract.WebAppContract;
import com.myd.cobiwebapps.data.source.repository.WebAppRepo;
import com.myd.cobiwebapps.webapps.model.Location;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by MYD on 11/22/17.
 *
 */

public class LocationPresenter implements WebAppContract.Presenter<Location> {

    private CompositeDisposable subscriptions = new CompositeDisposable();
    private WebAppRepo<Location> repository;
    private WebAppContract.View<Location> view;

    @Inject
    public LocationPresenter(WebAppRepo<Location> repository, WebAppContract.View<Location> view) {
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
    public void addData(Location model) {
        subscriptions.add(repository.addData(model)
                .subscribe(
                        x -> {
                            if (!x.isEmpty()) {
                                view.showData(x);
                            }
                        }
                ));

    }
}
