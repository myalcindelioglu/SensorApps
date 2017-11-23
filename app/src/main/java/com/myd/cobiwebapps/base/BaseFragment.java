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

/**
 * Created by MYD on 11/22/17.
 *
 */

public abstract class BaseFragment<T extends BaseModel>
        extends Fragment implements WebAppContract.View<T> {



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

        return view;
    }
}
