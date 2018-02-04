package com.br.beerlist.beerlist.api

import com.br.beerlist.beerlist.models.Beer
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*


interface PunkBeersApi {

    @GET("beers")
    fun getBearsByName(@Query("beer_name") email: String) : Observable<List<Beer>>
}