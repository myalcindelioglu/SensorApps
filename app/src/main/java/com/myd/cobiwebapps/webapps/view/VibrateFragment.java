package com.myd.cobiwebapps.webapps.view;


import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.myd.cobiwebapps.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.VIBRATOR_SERVICE;

public class VibrateFragment extends Fragment {

    private static final long VIBRATE_TIME = 800L;

    private Unbinder butterKnifeUnBinder;

    @BindView(R.id.fragment_vibrate_button)
    Button button;

    public VibrateFragment() {
        // Required empty public constructor
    }

    public static VibrateFragment newInstance() {
        VibrateFragment fragment = new VibrateFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vibrate, container, false);
        butterKnifeUnBinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        butterKnifeUnBinder.unbind();
    }


    @SuppressWarnings("ConstantConditions")
    @OnClick(R.id.fragment_vibrate_button)
    void clickOnVibrateButton() {
        ((Vibrator)getContext().getSystemService(VIBRATOR_SERVICE)).vibrate(VIBRATE_TIME);
    }

}
