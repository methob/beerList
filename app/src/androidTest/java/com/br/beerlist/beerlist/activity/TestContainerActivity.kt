package com.br.beerlist.beerlist.activity

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.DaggerBaseLayerComponent
import android.support.test.runner.AndroidJUnit4
import com.br.beerlist.beerlist.di.component.TestComponent
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class TestContainerActivity {

    private lateinit var testAppComponent: TestComponent

    @Before
    fun initDependencies() {

        MockitoAnnotations.initMocks(this)
        val app = InstrumentationRegistry.getTargetContext().applicationContext
//        DaggerBaseLayerComponent.builder().
    }

    @Test
    fun testSearchView() {

    }
}