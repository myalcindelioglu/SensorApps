package com.myd.cobiwebapps.webapps.view;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.LocationRequest;
import com.myd.cobiwebapps.base.BaseFragment;
import com.myd.cobiwebapps.webapps.model.Location;
import com.myd.cobiwebapps.webapps.presenter.LocationPresenter;
import com.patloew.rxlocation.RxLocation;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by MYD on 11/22/17.
 *
 */

public class LocationFragment extends BaseFragment<Location> {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    LocationPresenter presenter;

    @Inject
    RxPermissions rxPermissions;

    @Inject
    RxLocation rxLocation;

    public static LocationFragment newInstance() {
        Bundle args = new Bundle();
        LocationFragment fragment = new LocationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        presenter.subscribe();
        presenter.updateData();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        getLocationEverySec();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
        presenter.unSubscribe();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showData(String data) {
        progressBar.setVisibility(View.INVISIBLE);
        textView.append(data + "\n");
        textView.setVisibility(View.VISIBLE);
        emptyTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmptyState() {
        progressBar.setVisibility(View.INVISIBLE);
        emptyTextView.setVisibility(View.VISIBLE);
        textView.setVisibility(View.INVISIBLE);
    }

    @SuppressWarnings("ConstantConditions")
    private void getLocationEverySec() {
        compositeDisposable.add(
        Observable.interval(1000L, TimeUnit.MILLISECONDS).timeInterval()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(x ->  {
            Disposable disposable = rxPermissions
                    .request(Manifest.permission.ACCESS_COARSE_LOCATION)
                    .flatMap(granted -> {
                        LocationRequest locationRequest = LocationRequest.create()
                                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                        if (granted) {
                            //noinspection MissingPermission
                            if (ActivityCompat.checkSelfPermission(getContext(),
                                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                                    && ActivityCompat.checkSelfPermission(getContext(),
                                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                return Observable.empty();
                            }
                            return rxLocation
                                    .location()
                                    .updates(locationRequest)
                                    .firstElement()
                                    .toObservable();
                        } else {
                            return Observable.empty();
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(loc -> {
                        Location location = new Location(
                                loc.getLatitude(),
                                loc.getLongitude(),
                                System.currentTimeMillis());
                        presenter.addData(location);
                    }, e -> showError());
            compositeDisposable.add(disposable);

        }));
    }
}
