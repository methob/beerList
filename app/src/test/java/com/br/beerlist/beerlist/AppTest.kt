package com.br.beerlist.beerlist

import android.content.Context
import com.br.beerlist.beerlist.utils.NetworkManager
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AppTest {

    @Mock
    lateinit var context: Context

    @Before
    fun initClass() {

    }

    @Test
    fun checkInternetConnection() {

    }
}