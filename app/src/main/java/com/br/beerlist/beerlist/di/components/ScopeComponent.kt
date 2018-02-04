package com.br.beerlist.beerlist.di.components

import com.br.beerlist.beerlist.BeerApplication
import com.br.beerlist.beerlist.di.modules.ApplicationModule
import com.br.beerlist.beerlist.di.modules.BeerModule
import com.br.beerlist.beerlist.services.BeerService
import com.br.beerlist.beerlist.services.CloudRetrofit
import com.br.beerlist.beerlist.services.DatabaseRealm
import dagger.Component
import javax.inject.Singleton

/**
 * Created by jonathan on 01/02/2018.
 */
@Singleton
@Component(modules = arrayOf( ApplicationModule::class, BeerModule::class))
interface ScopeComponent {

    fun inject(service: BeerService)
    fun inject(application: BeerApplication)

    fun inject(cloudRetrofit: CloudRetrofit)
    fun inject(databaseRealm: DatabaseRealm)
}