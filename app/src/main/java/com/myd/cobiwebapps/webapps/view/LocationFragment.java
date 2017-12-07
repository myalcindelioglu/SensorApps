package com.myd.cobiwebapps.webapps.view;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.myd.cobiwebapps.R;
import com.myd.cobiwebapps.base.BaseFragment;
import com.myd.cobiwebapps.webapps.model.Location;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by MYD on 11/22/17.
 *
 */

public class LocationFragment extends BaseFragment<Location> {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    RxPermissions rxPermissions;

    @BindView(R.id.fragment_location_text_view)
    public TextView textView;

    @BindView(R.id.fragment_location_empty_text_view)
    public TextView emptyTextView;

    @BindView(R.id.fragment_location_progress_bar)
    public ProgressBar progressBar;

    private Unbinder butterKnifeUnBinder;

    public static LocationFragment newInstance() {
        Bundle args = new Bundle();
        LocationFragment fragment = new LocationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        getLocationEverySec();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        butterKnifeUnBinder = ButterKnife.bind(this, view);

        textView.setMovementMethod(new ScrollingMovementMethod());

        presenter.subscribe();
        presenter.fetchAndUpdate();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
        presenter.unSubscribe();
        butterKnifeUnBinder.unbind();
    }

    private void getLocationEverySec() {
        compositeDisposable.add(
        Observable.interval(1000L, TimeUnit.MILLISECONDS).timeInterval()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(x ->  {
            Disposable disposable = rxPermissions
                    .request(Manifest.permission.ACCESS_FINE_LOCATION)
                    .subscribe(granted -> {
                        if (granted) {
                            presenter.getDataAndUpdate();
                        }
                    }, e -> showError());
            compositeDisposable.add(disposable);

        }));
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showData(Location data) {
        progressBar.setVisibility(View.INVISIBLE);
        textView.append(data.toString() + "\n");
        textView.setVisibility(View.VISIBLE);
        emptyTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showEmptyState() {
        progressBar.setVisibility(View.INVISIBLE);
        emptyTextView.setVisibility(View.VISIBLE);
        textView.setVisibility(View.INVISIBLE);
    }
}
