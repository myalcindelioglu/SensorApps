package com.myd.sensorapps;

import com.myd.sensorapps.data.source.local.LocalAppSource;
import com.myd.sensorapps.data.source.repository.AppRepo;
import com.myd.sensorapps.data.source.sensor.SensorAppSource;
import com.myd.sensorapps.coreapps.model.Battery;

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

public class AppRepoTest {
    private AppRepo<Battery> repo;

    @Mock
    private LocalAppSource<Battery> localAppSource;

    @Mock
    private SensorAppSource<Battery> sensorAppSource;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        repo = new AppRepo<>(localAppSource, sensorAppSource);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetData() throws Exception {
        repo.getData();
    }

    @Test
    public void testAddData() throws Exception {
        Battery battery = AppTestUtils.generateBattery(false);
        when(localAppSource.addData(battery)).then(x -> Single.just(battery));
        Single<Battery> batterySingle = repo.addData(battery);

        TestObserver<Battery> test = batterySingle.test();
        test.assertNoErrors();
        test.assertValue(battery);
        test.assertComplete();
    }

    @Test
    public void testUpdateInfo() throws Exception {
        Battery battery = AppTestUtils.generateBattery(true);
        when(localAppSource.getData()).then(x ->
                Single.just(Collections.singletonList(battery)));
        TestObserver<List<Battery>> test = repo.updateInfo().test();
        test.assertNoErrors();
        test.assertValue(Collections.singletonList(battery));
        test.assertComplete();
    }
}
