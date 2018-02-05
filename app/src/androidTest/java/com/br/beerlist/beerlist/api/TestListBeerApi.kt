package com.br.beerlist.beerlist.api

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.br.beerlist.beerlist.activities.ContainerBeerActivity
import com.br.beerlist.beerlist.models.Beer
import com.br.beerlist.beerlist.services.BeerService
import com.br.beerlist.beerlist.utils.BuildConfigApp
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class TestListBeerApi {

    @Rule
    val rule = ActivityTestRule<ContainerBeerActivity>(ContainerBeerActivity::class.java)

    private lateinit var service: BeerService

    private lateinit var context: Context

    @Before
    fun initConfig() {

        service = BeerService()
        context = InstrumentationRegistry.getTargetContext()


    }

    @Throws(IOException::class)
    @Test
    fun getBeerTestRequest() {

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BuildConfigApp.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        val retrofitInstance = retrofit.create(PunkBeersApi::class.java)

        val listBeers: Observable<List<Beer>> = retrofitInstance.getBearsByName("beer")

        listBeers.subscribe(
                {
                    result ->
                    Assert.assertTrue(!result.isEmpty())
                },
                {
                    error ->
                    Assert.assertTrue(true)
                }
        )
    }
}