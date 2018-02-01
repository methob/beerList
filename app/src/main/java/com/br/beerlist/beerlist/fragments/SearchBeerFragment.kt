package com.br.beerlist.beerlist.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.br.beerlist.beerlist.R


/**
 * A simple [Fragment] subclass.
 */
class SearchBeerFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_beer, container, false)
    }

}// Required empty public constructor
