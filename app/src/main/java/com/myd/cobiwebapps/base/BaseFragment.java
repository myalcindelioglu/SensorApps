package com.myd.cobiwebapps.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.myd.cobiwebapps.R;
import com.myd.cobiwebapps.contract.WebAppContract;

import javax.inject.Inject;

/**
 * Created by MYD on 11/22/17.
 *
 */

public abstract class BaseFragment<T extends BaseModel>
        extends Fragment implements WebAppContract.View<T> {

    @Inject
    public WebAppContract.Presenter<T> presenter;

    public TextView textView;
    public TextView emptyTextView;

    public ProgressBar progressBar;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, container, false);

        textView = view.findViewById(R.id.fragment_text_text_view);
        textView.setMovementMethod(new ScrollingMovementMethod());

        emptyTextView = view.findViewById(R.id.fragment_text_empty_text_view);

        progressBar = view.findViewById(R.id.fragment_text_progress_bar);

        presenter.subscribe();
        presenter.updateData();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
}
