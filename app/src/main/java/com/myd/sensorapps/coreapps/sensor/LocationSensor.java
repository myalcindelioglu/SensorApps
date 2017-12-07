package com.myd.sensorapps.coreapps.sensor;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.LocationRequest;
import com.myd.sensorapps.base.BaseSensor;
import com.myd.sensorapps.coreapps.model.Location;
import com.patloew.rxlocation.RxLocation;

import javax.inject.Inject;

import io.reactivex.Maybe;

/**
 * Created by MYD on 12/7/17.
 *
 */

public class LocationSensor implements BaseSensor<Location> {

    private RxLocation rxLocation;

    private Context context;

    @Inject
    public LocationSensor(Context context, RxLocation rxLocation) {
        this.context = context;
        this.rxLocation = rxLocation;
    }

    @Override
    public Maybe<Location> getSingleData() {
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            return Maybe.empty();
        }

        return rxLocation
                .location()
                .updates(locationRequest)
                .firstElement()
                .flatMap(loc -> {
                    Location location = new Location(
                            loc.getLatitude(),
                            loc.getLongitude(),
                            System.currentTimeMillis()
                    );
                    return Maybe.just(location);
                });
    }
}
