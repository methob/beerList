package com.br.beerlist.beerlist

import android.app.Application
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import io.realm.Realm
import io.realm.Realm.setDefaultConfiguration
import io.realm.RealmConfiguration



/**
 * Created by jonathan on 31/01/2018.
 */
class BeerApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initRealmConfiguration()
        MultiDex.install(this)
    }

    private fun initRealmConfiguration() {
        Realm.init(this)

        val realmConfiguration = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }
}