package com.br.beerlist.beerlist.services

import com.br.beerlist.beerlist.R
import com.br.beerlist.beerlist.di.Injector
import com.br.beerlist.beerlist.models.Beer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

// Metodos para acesso ao banco de dados e api rest
class BeerService {

    @Inject
    lateinit var cloud: CloudRetrofit

    @Inject
    lateinit var database: DatabaseRealm

    private val mCompositeDisposable = CompositeDisposable()

    init {
        Injector.component.inject(this)
    }

    // Request para obter o nome do repositorio,
    fun getBeersByName(name: String, mListener: IListenerResponseBeers) {

        if (cloud.netWorkManager().isConnected()) {

            mCompositeDisposable.add(
                    cloud.getApiInstance().getBearsByName(name).
                            observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(
                                    {
                                        beers -> mListener.onResponseSuccessfully(beers)
                                    },
                                    {
                                        error -> mListener.onResponseFailed(error.message!!)
                                    }
                            )
            )
        } else {

            mListener.onResponseFailed(cloud.context.getString(R.string.txt_no_connection))
        }
    }

    // para de ouvir os eventos
    fun clearContentDisposable() {
        mCompositeDisposable.clear()
    }

    // Obtem uma beer do banco
    fun getBeerByIDFromDatabase(id: Int) : Beer? {
        return database.getRealmInstance().where(Beer::class.java).equalTo("id", id).findFirst()
    }

    // Listener para resposta do request
    interface IListenerResponseBeers  {

        fun onResponseSuccessfully(beers: List<Beer>)
        fun onResponseFailed(message:String)
    }
}