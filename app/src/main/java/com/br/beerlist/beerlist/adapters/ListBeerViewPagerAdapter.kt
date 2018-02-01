package com.br.beerlist.beerlist.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.br.beerlist.beerlist.fragments.FavoritesFragment
import com.br.beerlist.beerlist.fragments.SearchBeerFragment

/**
 * Created by jonathan on 01/02/2018.
 */
class ListBeerViewPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return SearchBeerFragment()
            1 -> return FavoritesFragment()
            else -> {
                return SearchBeerFragment()
            }
        }
    }

    override fun getCount(): Int {
            return 2
    }
}