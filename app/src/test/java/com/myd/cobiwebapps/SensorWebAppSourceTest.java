package com.myd.cobiwebapps;

import com.myd.cobiwebapps.data.source.sensor.SensorWebAppSource;
import com.myd.cobiwebapps.webapps.model.Accelerometer;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Single;

/**
 * Created by MYD on 11/26/17.
 *
 */

public class SensorWebAppSourceTest {

    private SensorWebAppSource<Accelerometer> source;

    @Before
    public void setUp() throws Exception {
        source = new SensorWebAppSource<>();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetData() throws Exception {
        source.getData();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdateInfo() throws Exception {
        source.updateInfo();
    }

    @Test
    public void testAddData() throws Exception {
        Accelerometer accelerometer = WebAppTestUtils.generateAccelerometer();
        Single<String> addedData = source.addData(accelerometer);
        addedData.test().assertValue(accelerometer.toString());
    }
}
