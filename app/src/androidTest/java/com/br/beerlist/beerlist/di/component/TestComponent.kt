package com.br.beerlist.beerlist.di.component

import com.br.beerlist.beerlist.activity.TestContainerActivity
import com.br.beerlist.beerlist.di.module.InjectMockModule
import com.br.beerlist.beerlist.di.modules.ApplicationModule
import com.br.beerlist.beerlist.di.modules.BeerModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(ApplicationModule::class, BeerModule::class, InjectMockModule::class))
interface TestComponent {

    fun inject(activity: TestContainerActivity)

}