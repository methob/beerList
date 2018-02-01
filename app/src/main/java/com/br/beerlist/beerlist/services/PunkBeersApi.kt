package com.br.beerlist.beerlist.services

import com.br.beerlist.beerlist.models.Beer
import retrofit2.Call
import retrofit2.http.*


interface PunkBeersApi {

    @GET("{beer_name}")
    fun getBearsByName(@Path("beer_name") email: String) : Call<Beer>

}