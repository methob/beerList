package com.br.beerlist.beerlist.fragments

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment


@Suppress("UNCHECKED_CAST")

/* Activity para se colocar metodos comuns a todas as fragments do projeto, nesse projeto não houve.*/
open class BaseFragment<T> : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var activity: T = activity as T
    }

}