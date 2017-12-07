package com.myd.cobiwebapps;

import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.runner.AndroidJUnit4;

import com.myd.cobiwebapps.data.source.local.LocalWebAppSource;
import com.myd.cobiwebapps.webapps.model.Location;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.observers.TestObserver;
import io.realm.Realm;
import io.realm.RealmConfiguration;

import static junit.framework.Assert.assertEquals;

/**
 * Created by MYD on 11/26/17.
 *
 */

@RunWith(AndroidJUnit4.class)
public class LocalWebAppSourceTest {
    private LocalWebAppSource<Location> source;

    private Realm realm;

    private Class<Location> clazz;

    @Before
    @UiThreadTest
    public void setUp() throws Exception {
        Realm.init(InstrumentationRegistry.getTargetContext());
        RealmConfiguration config = new RealmConfiguration.Builder().inMemory().build();
        realm = Realm.getInstance(config);
        clazz = Location.class;
        source = new LocalWebAppSource<>(realm, clazz);
    }

    @After
    @UiThreadTest
    public void tearDown() {
        realm.close();
        Realm.deleteRealm(realm.getConfiguration());
    }

    @Test
    public void testGetData() throws Exception {
        Location location = WebAppTestUtils.generateLocation();
        save2Db(location);
        TestObserver<List<Location>> test = source.getData().test();
        test.assertNoErrors();
        test.assertValueCount(1);
        assertEquals(location.getId(), test.values().get(0).get(0).getId());
        assertEquals(location.getLat(), test.values().get(0).get(0).getLat());
        assertEquals(location.getLon(), test.values().get(0).get(0).getLon());

    }

    @Test
    public void testAddData() throws Exception {
        Location location = WebAppTestUtils.generateLocation();
        TestObserver<Location> test = source.addData(location).test();
        test.assertNoErrors();
        test.assertComplete();
        test.assertValueCount(1);
        Location expected = getFromDb().get(0);
        assertEquals(expected.getId(), location.getId());
        assertEquals(expected.getLat(), location.getLat());
        assertEquals(expected.getLon(), location.getLon());
        assertEquals(expected.getDate(), location.getDate());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdateInfo() throws Exception {
        source.updateInfo();
    }

    private void save2Db(Location location) {
        realm.executeTransaction(x -> realm.copyToRealm(location));
    }

    private List<Location> getFromDb() {
        return realm.where(Location.class).findAll();
    }
}
