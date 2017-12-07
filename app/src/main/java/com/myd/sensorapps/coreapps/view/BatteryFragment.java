package com.myd.sensorapps.coreapps.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.myd.sensorapps.R;
import com.myd.sensorapps.base.BaseFragment;
import com.myd.sensorapps.coreapps.model.Battery;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class BatteryFragment extends BaseFragment<Battery> {

    private static final String TAG = "BatteryFragment";
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.fragment_battery_text_view)
    public TextView textView;

    @BindView(R.id.fragment_battery_empty_text_view)
    public TextView emptyTextView;

    @BindView(R.id.fragment_battery_progress_bar)
    public ProgressBar progressBar;

    private Unbinder butterKnifeUnBinder;

    public static BatteryFragment newInstance() {
        Bundle args = new Bundle();
        BatteryFragment fragment = new BatteryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_battery, container, false);
        butterKnifeUnBinder = ButterKnife.bind(this, view);

        textView.setMovementMethod(new ScrollingMovementMethod());

        presenter.subscribe();
        presenter.fetchAndUpdate();

        return view;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);

        compositeDisposable.add(
                Observable.interval(1000L, TimeUnit.MILLISECONDS).timeInterval()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(x -> presenter.getDataAndUpdate()));

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
        butterKnifeUnBinder.unbind();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showData(Battery data) {
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
