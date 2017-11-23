package com.myd.cobiwebapps.data.realm;

/*
 * Created by MYD on 11/20/17.
 *
 */

import android.util.Log;

import javax.inject.Singleton;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

@Singleton
public class DbMigration implements RealmMigration {

    private static final String TAG = "RmMigration";

    @Override
    public void migrate(final DynamicRealm realm, long oldVersion, long newVersion) {
        Log.i(TAG, "migration from " + oldVersion + " to " + newVersion);
    }
}
