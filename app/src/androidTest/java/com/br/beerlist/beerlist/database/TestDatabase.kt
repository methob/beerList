package com.br.beerlist.beerlist.database

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.br.beerlist.beerlist.models.Beer
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TestDatabase {

    lateinit var context: Context

    @Before
    fun initConfig() {
        context = InstrumentationRegistry.getContext()
        Realm.init(context)

        val realmConfiguration = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()

        Realm.setDefaultConfiguration(realmConfiguration)
    }

    @Test
    fun testReturnDatabase() {

        val beers: RealmResults<Beer> = Realm.getDefaultInstance().where(Beer::class.java).findAll()
        Assert.assertTrue(beers.isLoaded)
    }


}