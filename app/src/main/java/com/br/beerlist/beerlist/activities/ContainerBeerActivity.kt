package com.br.beerlist.beerlist.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.br.beerlist.beerlist.R
import com.br.beerlist.beerlist.fragments.FavoritesFragment
import com.br.beerlist.beerlist.fragments.SearchBeerFragment
import kotlinx.android.synthetic.main.activity_list_beer.*

class ContainerBeerActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_beer)

        nav_view.setOnNavigationItemSelectedListener(this)

        nav_view.selectedItemId = R.id.menu_search

        setSupportActionBar(toolbar)
    }

    private fun replaceFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, fragment)
        ft.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_search -> {
                replaceFragment(SearchBeerFragment())
            }
            R.id.menu_favorite -> {
                search_view.closeSearch()
                replaceFragment(FavoritesFragment())
            }
        }
        return true
    }
}

