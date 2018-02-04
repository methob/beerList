package com.br.beerlist.beerlist

import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.br.beerlist.beerlist.di.Injector
import com.br.beerlist.beerlist.services.DatabaseRealm
import javax.inject.Inject


class BeerApplication : MultiDexApplication() {

    @Inject
    lateinit var database: DatabaseRealm

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        initDagger()
        initRealm()
    }

    private fun initRealm() {

        database.initRealmConfiguration()
    }

    private fun initDagger() {
        Injector.initializeApplicationComponent(this)
        Injector.component.inject(this)
    }
}