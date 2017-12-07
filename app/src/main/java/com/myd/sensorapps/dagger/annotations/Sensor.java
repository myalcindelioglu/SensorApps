package com.myd.sensorapps.dagger.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by MYD on 11/20/17.
 *
 */

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Sensor {
}
