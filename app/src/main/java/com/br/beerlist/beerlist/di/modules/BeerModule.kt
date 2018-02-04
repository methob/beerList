package com.br.beerlist.beerlist.di.modules

import com.br.beerlist.beerlist.services.CloudRetrofit
import com.br.beerlist.beerlist.services.DatabaseRealm
import com.br.beerlist.beerlist.utils.NetworkManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by jonathan on 03/02/2018.
 */
@Module
class BeerModule {

    @Singleton
    @Provides
    fun databaseModule(): DatabaseRealm {
        return DatabaseRealm()
    }

    @Singleton
    @Provides
    fun cloudRetrofit(): CloudRetrofit {
        return CloudRetrofit()
    }
}