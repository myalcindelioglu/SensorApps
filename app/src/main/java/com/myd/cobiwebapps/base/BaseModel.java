package com.myd.cobiwebapps.base;

import io.realm.RealmModel;

/**
 * Created by MYD on 11/20/17.
 *
 */

public interface BaseModel extends RealmModel {
    Integer getId();
    void setId(Integer id);
    Long getDate();
}
