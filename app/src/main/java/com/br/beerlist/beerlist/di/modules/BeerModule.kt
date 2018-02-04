package com.br.beerlist.beerlist.di.modules

import com.br.beerlist.beerlist.services.CloudRetrofit
import com.br.beerlist.beerlist.services.DatabaseRealm
import com.br.beerlist.beerlist.utils.NetworkManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/* Provém modules de banco de dados e da API RESTFUL*/
@Module
class BeerModule {

    @Singleton
    @Provides
    fun databaseRealm(): DatabaseRealm {
        return DatabaseRealm()
    }

    @Singleton
    @Provides
    fun cloudRetrofit(): CloudRetrofit {
        return CloudRetrofit()
    }
}