package com.br.beerlist.beerlist.dagger.components

import com.br.beerlist.beerlist.dagger.modules.DatabaseModule
import com.br.beerlist.beerlist.dagger.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by jonathan on 01/02/2018.
 */
@Singleton
@Component(modules = arrayOf(NetworkModule::class, DatabaseModule::class))
interface ScopeComponent {

}