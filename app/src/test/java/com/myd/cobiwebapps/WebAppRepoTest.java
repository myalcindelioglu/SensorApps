package com.myd.cobiwebapps;

import com.myd.cobiwebapps.data.source.local.LocalWebAppSource;
import com.myd.cobiwebapps.data.source.repository.WebAppRepo;
import com.myd.cobiwebapps.data.source.sensor.SensorWebAppSource;
import com.myd.cobiwebapps.webapps.model.Battery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.when;

/**
 * Created by MYD on 11/26/17.
 *
 */

public class WebAppRepoTest {
    private WebAppRepo<Battery> repo;

    @Mock
    private LocalWebAppSource<Battery> localWebAppSource;

    @Mock
    private SensorWebAppSource<Battery> sensorWebAppSource;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        repo = new WebAppRepo<>(localWebAppSource, sensorWebAppSource);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetData() throws Exception {
        repo.getData();
    }

    @Test
    public void testAddData() throws Exception {
        Battery battery = WebAppTestUtils.generateBattery(false);
        when(localWebAppSource.addData(battery)).then(x -> Single.just(battery));
        Single<Battery> batterySingle = repo.addData(battery);

        TestObserver<Battery> test = batterySingle.test();
        test.assertNoErrors();
        test.assertValue(battery);
        test.assertComplete();
    }

    @Test
    public void testUpdateInfo() throws Exception {
        Battery battery = WebAppTestUtils.generateBattery(true);
        when(localWebAppSource.getData()).then(x ->
                Single.just(Collections.singletonList(battery)));
        TestObserver<List<Battery>> test = repo.updateInfo().test();
        test.assertNoErrors();
        test.assertValue(Collections.singletonList(battery));
        test.assertComplete();
    }
}
