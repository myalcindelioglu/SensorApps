package com.myd.cobiwebapps;

import com.myd.cobiwebapps.base.BaseSensor;
import com.myd.cobiwebapps.data.source.sensor.SensorWebAppSource;
import com.myd.cobiwebapps.webapps.model.Accelerometer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Maybe;

import static org.mockito.Mockito.when;

/**
 * Created by MYD on 11/26/17.
 *
 */

public class SensorWebAppSourceTest {

    private SensorWebAppSource<Accelerometer> source;

    @Mock
    private BaseSensor<Accelerometer> sensor;

    private Accelerometer accelerometer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        accelerometer = WebAppTestUtils.generateAccelerometer();
        source = new SensorWebAppSource<>(sensor);
    }

    @Test
    public void testGetSingleData() throws Exception {
        when(sensor.getSingleData()).then(x -> Maybe.just(accelerometer));
        Maybe<Accelerometer> singleData = source.getSingleData();
        singleData.test().assertValue(accelerometer);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetData() throws Exception {
        source.getData();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdateInfo() throws Exception {
        source.updateInfo();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddData() throws Exception {
        source.addData(accelerometer);
    }
}
