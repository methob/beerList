package com.br.beerlist.beerlist.services

import com.br.beerlist.beerlist.models.Beer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class BeerService : BaseService() {


    fun getBeersByName(name:String) {

        val call = getApiInstance().getBearsByName(name)

        call.enqueue(object : Callback<Beer> {

            override fun onResponse(call: Call<Beer>, response: Response<Beer>) {

                if (response.isSuccessful) {

                } else {

                }
            }

            override fun onFailure(call: Call<Beer>, t: Throwable) {

            }
        })
    }
}