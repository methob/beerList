package com.br.beerlist.beerlist.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.br.beerlist.beerlist.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Observable.just(true).
                delay(2, TimeUnit.SECONDS).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                map {
                    callListBeersActivity()
                }.subscribe()
    }

    fun callListBeersActivity() {
        val intent = Intent(this@SplashActivity, ListBeerActivity::class.java)
        startActivity(intent)
    }
}

