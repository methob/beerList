package com.br.beerlist.beerlist.di.modules

import android.content.Context
import com.br.beerlist.beerlist.BeerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// Modulo da aplicação, provém contexto e uma instancia da própria aplicação
@Module
class ApplicationModule(var application: BeerApplication) {

    @Provides
    @Singleton
    fun application(): BeerApplication {
        return application
    }

    @Provides
    @Singleton
    fun applicationContext(): Context {
        return application.applicationContext
    }

}
