package com.myd.cobiwebapps.data.source.local;

import android.support.annotation.NonNull;

import com.myd.cobiwebapps.base.BaseModel;
import com.myd.cobiwebapps.data.source.WebAppSource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by MYD on 11/20/17.
 *
 */

public class LocalWebAppSource<T extends BaseModel>
        implements WebAppSource<T> {
    private final Realm realm;
    private final Class<T> clazz;

    @Inject
    public LocalWebAppSource(@NonNull Realm realm,
                             @NonNull Class<T> clazz) {
        this.realm = realm;
        this.clazz = clazz;
    }

    @Override
    public Single<List<T>> getData() {
        RealmResults<T> models = realm.where(clazz).findAll();
        return models.isEmpty() ?
                Single.just(new ArrayList<>()) :
                Single.just(realm.copyFromRealm(models));
    }

    @Override
    public Maybe<T> getSingleData() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Single<T> addData(T model) {
        int nextId = getNextKey();
        model.setId(nextId);
        realm.executeTransaction(x -> realm.insertOrUpdate(model));
        return Single.just(model);
    }

    @Override
    public Single<List<T>> updateInfo() {
        throw new UnsupportedOperationException();
    }

    private int getNextKey() {
        try {
            Number number = realm.where(clazz).max("id");
            if (number != null) {
                return number.intValue() + 1;
            } else {
                return 0;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }

}
