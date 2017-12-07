package com.myd.cobiwebapps;

import com.myd.cobiwebapps.contract.WebAppContract;
import com.myd.cobiwebapps.data.source.repository.WebAppRepo;
import com.myd.cobiwebapps.webapps.model.Accelerometer;
import com.myd.cobiwebapps.webapps.presenter.Presenter;

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
    WebAppRepo<Accelerometer> webAppRepo;

    @Mock
    private
    WebAppContract.View<Accelerometer> view;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new Presenter<>(webAppRepo, view);
    }

    @Test
    public void updateDataShouldShowProgressBar() throws Exception {
        Accelerometer accelerometer = WebAppTestUtils.generateAccelerometer();
        List<Accelerometer> accelerometerList = Collections.singletonList(accelerometer);
        when(webAppRepo.updateInfo()).thenReturn(Single.just(accelerometerList));
        presenter.fetchAndUpdate();
        verify(view, times(1)).showProgress();
    }

    @Test
    public void updateDataShouldShowEmptyState() throws Exception {
        List<Accelerometer> accelerometerList = new ArrayList<>();
        when(webAppRepo.updateInfo()).thenReturn(Single.just(accelerometerList));
        presenter.fetchAndUpdate();
        verify(view, times(1)).showProgress();
        verify(view, times(1)).showEmptyState();
    }

    @Test
    public void updateDataShouldShowData() throws Exception {
        Accelerometer accelerometer = WebAppTestUtils.generateAccelerometer();
        List<Accelerometer> accelerometerList = Collections.singletonList(accelerometer);
        when(webAppRepo.updateInfo()).thenReturn(Single.just(accelerometerList));
        presenter.fetchAndUpdate();
        verify(view, times(1)).showProgress();
        verify(view, times(1)).showData(accelerometer);
    }

    @Test
    public void addDataShouldShowData() throws Exception {
        Accelerometer accelerometer = WebAppTestUtils.generateAccelerometer();
        when(webAppRepo.addData(accelerometer)).thenReturn(Single.just(accelerometer));
        presenter.addDataAndUpdate(accelerometer);
        verify(view, times(1)).showData(accelerometer);
    }

}
