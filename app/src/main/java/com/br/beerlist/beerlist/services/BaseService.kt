package com.br.beerlist.beerlist.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import io.realm.RealmObject
import com.google.gson.FieldAttributes
import com.google.gson.ExclusionStrategy
import com.google.gson.GsonBuilder
import com.google.gson.Gson




open class BaseService {

    companion object {
        val BASE_URL:String = "https://api.punkapi.com/v2/"
    }

    lateinit var retrofit: Retrofit


    fun getApiInstance(): PunkBeersApi {

        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(getGsonObjectWithoutRealmGarbagParams()))
                .build()

        return retrofit.create(PunkBeersApi::class.java)
    }

    fun getGsonObjectWithoutRealmGarbagParams(): Gson {
        return GsonBuilder().setExclusionStrategies(object : ExclusionStrategy {
            override fun shouldSkipField(f: FieldAttributes): Boolean {
                return f.declaringClass == RealmObject::class.java
            }

            override fun shouldSkipClass(clazz: Class<*>): Boolean {
                return false
            }
        }).create()
    }

}