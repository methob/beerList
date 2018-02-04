package com.br.beerlist.beerlist.utils

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NetworkTest {

    lateinit var networkManager: NetworkManager

    @Test
    fun testConnection() {
        networkManager = NetworkManager(InstrumentationRegistry.getContext())
        if (networkManager.isConnected()) {
            assert(true)
        }
    }

}