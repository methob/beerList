package com.br.beerlist.beerlist

import android.content.Context
import com.br.beerlist.beerlist.models.Beer
import io.realm.Realm
import org.junit.Test
import io.realm.RealmConfiguration
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RealmTest {

    @Mock
    lateinit var context: Context

    lateinit var testConfig: RealmConfiguration

    @Before
    fun initConfig() {

        Realm.init(context)
        testConfig = RealmConfiguration.Builder().inMemory().name("test-realm").build()
    }

    @Test
    fun testRealmFunction() {
        val testRealm = Realm.getInstance(testConfig)
        val realmObject = testRealm.where(Beer::class.java).findFirst()

        if (realmObject != null)
            assert(true)
    }

}