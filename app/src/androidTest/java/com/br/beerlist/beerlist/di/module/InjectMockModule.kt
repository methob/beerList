package com.br.beerlist.beerlist.di.module

import com.br.beerlist.beerlist.models.Beer
import dagger.Module
import dagger.Provides
import org.junit.Test


@Module
class InjectMockModule {

    @Test
    @Provides
    fun mockBeer() : Beer {

        val beer : Beer = Beer()
        beer.name = "testename"
        beer.description = "testdescription"
        beer.tagline = "testtagline"
        beer.id = 1

        return beer
    }
}