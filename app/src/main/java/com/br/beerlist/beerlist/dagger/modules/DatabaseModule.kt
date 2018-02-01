package com.br.beerlist.beerlist.dagger.modules

import dagger.Module
import dagger.Provides
import io.realm.Realm

/**
 * Created by jonathan on 01/02/2018.
 */
@Module
class DatabaseModule {

    @Provides
    internal fun provideRealm(): Realm {
        return Realm.getDefaultInstance()
    }

}