package com.br.beerlist.beerlist.di

import com.br.beerlist.beerlist.BeerApplication
import com.br.beerlist.beerlist.di.components.DaggerScopeComponent
import com.br.beerlist.beerlist.di.components.ScopeComponent
import com.br.beerlist.beerlist.di.modules.ApplicationModule
import com.br.beerlist.beerlist.di.modules.BeerModule


class Injector {

    companion object {

        lateinit var component: ScopeComponent

        fun initializeApplicationComponent(customApplication: BeerApplication) {

            component = DaggerScopeComponent.builder()
                    .applicationModule(ApplicationModule(customApplication))
                    .beerModule(BeerModule())
                    .build()
        }
    }
}