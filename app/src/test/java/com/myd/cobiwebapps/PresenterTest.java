package com.myd.cobiwebapps;

import android.text.TextUtils;

import com.myd.cobiwebapps.contract.WebAppContract;
import com.myd.cobiwebapps.data.source.repository.WebAppRepo;
import com.myd.cobiwebapps.webapps.model.Accelerometer;
import com.myd.cobiwebapps.webapps.presenter.Presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Single;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by MYD on 11/25/17.
 *
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)
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
        mockStatic(TextUtils.class);
        presenter = new Presenter<>(webAppRepo, view);
    }

    @Test
    public void updateDataShouldShowProgressBar() throws Exception {
        Accelerometer accelerometer = WebAppTestUtils.generateAccelerometer();
        List<String> accelerometerStringList = Collections.singletonList(accelerometer.toString());
        when(TextUtils.join("\n", accelerometerStringList)).thenAnswer(x -> accelerometer.toString() + "\n");
        when(webAppRepo.updateInfo()).thenReturn(Single.just(accelerometerStringList));
        presenter.updateData();
        verify(view, times(1)).showProgress();
    }

    @Test
    public void updateDataShouldShowEmptyState() throws Exception {
        List<String> accelerometerStringList = new ArrayList<>();
        when(webAppRepo.updateInfo()).thenReturn(Single.just(accelerometerStringList));
        presenter.updateData();
        verify(view, times(1)).showProgress();
        verify(view, times(1)).showEmptyState();
    }

    @Test
    public void updateDataShouldShowData() throws Exception {
        Accelerometer accelerometer = WebAppTestUtils.generateAccelerometer();
        List<String> accelerometerStringList = Collections.singletonList(accelerometer.toString());
        String data = TextUtils.join("\n", accelerometerStringList);
        when(data).thenAnswer(x -> accelerometer.toString() + "\n");
        when(webAppRepo.updateInfo()).thenReturn(Single.just(accelerometerStringList));
        presenter.updateData();
        verify(view, times(1)).showProgress();
        verify(view, times(1)).showData(data);
    }

    @Test
    public void addDataShouldShowData() throws Exception {
        Accelerometer accelerometer = WebAppTestUtils.generateAccelerometer();
        when(webAppRepo.addData(accelerometer)).thenReturn(Single.just(accelerometer.toString()));
        presenter.addData(accelerometer);
        verify(view, times(1)).showData(accelerometer.toString());
    }

}
