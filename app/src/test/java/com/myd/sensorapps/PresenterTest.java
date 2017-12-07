package com.myd.sensorapps;

import com.myd.sensorapps.contract.AppContract;
import com.myd.sensorapps.data.source.repository.AppRepo;
import com.myd.sensorapps.coreapps.model.Accelerometer;
import com.myd.sensorapps.coreapps.presenter.Presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Single;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by MYD on 11/25/17.
 *
 */

public class PresenterTest {

    private Presenter<Accelerometer> presenter;

    @Mock
    private
    AppRepo<Accelerometer> appRepo;

    @Mock
    private
    AppContract.View<Accelerometer> view;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new Presenter<>(appRepo, view);
    }

    @Test
    public void updateDataShouldShowProgressBar() throws Exception {
        Accelerometer accelerometer = AppTestUtils.generateAccelerometer();
        List<Accelerometer> accelerometerList = Collections.singletonList(accelerometer);
        when(appRepo.updateInfo()).thenReturn(Single.just(accelerometerList));
        presenter.fetchAndUpdate();
        verify(view, times(1)).showProgress();
    }

    @Test
    public void updateDataShouldShowEmptyState() throws Exception {
        List<Accelerometer> accelerometerList = new ArrayList<>();
        when(appRepo.updateInfo()).thenReturn(Single.just(accelerometerList));
        presenter.fetchAndUpdate();
        verify(view, times(1)).showProgress();
        verify(view, times(1)).showEmptyState();
    }

    @Test
    public void updateDataShouldShowData() throws Exception {
        Accelerometer accelerometer = AppTestUtils.generateAccelerometer();
        List<Accelerometer> accelerometerList = Collections.singletonList(accelerometer);
        when(appRepo.updateInfo()).thenReturn(Single.just(accelerometerList));
        presenter.fetchAndUpdate();
        verify(view, times(1)).showProgress();
        verify(view, times(1)).showData(accelerometer);
    }

    @Test
    public void addDataShouldShowData() throws Exception {
        Accelerometer accelerometer = AppTestUtils.generateAccelerometer();
        when(appRepo.addData(accelerometer)).thenReturn(Single.just(accelerometer));
        presenter.addDataAndUpdate(accelerometer);
        verify(view, times(1)).showData(accelerometer);
    }

}
