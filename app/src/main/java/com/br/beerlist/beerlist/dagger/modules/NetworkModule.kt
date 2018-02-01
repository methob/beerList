package com.br.beerlist.beerlist.dagger.modules

import android.content.Context
import com.br.beerlist.beerlist.services.BaseService
import com.br.beerlist.beerlist.services.PunkBeersApi
import com.br.beerlist.beerlist.utils.NetworkManager
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class NetworkModule(var context: Context) {

    @Singleton
    @Provides fun networkManager(): NetworkManager {
        return NetworkManager(context)
    }

    @Provides fun getApiInstance(): PunkBeersApi {
        val retrofit = Retrofit.Builder()
                .baseUrl(BaseService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(PunkBeersApi::class.java)
    }
}