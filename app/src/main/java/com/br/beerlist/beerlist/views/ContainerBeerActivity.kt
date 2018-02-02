package com.br.beerlist.beerlist.views

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.br.beerlist.beerlist.R
import com.br.beerlist.beerlist.adapters.ListBeerViewPagerAdapter
import com.br.beerlist.beerlist.adapters.ListBeersAdapter
import kotlinx.android.synthetic.main.activity_list_beer.*

class ContainerBeerActivity : AppCompatActivity() {

    lateinit var searchMenu: Menu
    lateinit var itemSearch: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_beer)

        setSupportActionBar(toolbar)

        container.adapter = ListBeerViewPagerAdapter(supportFragmentManager)
        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

//        setSupportActionBar(toolbar)
//        setSearchtoolbar()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)

        val item = menu.findItem(R.id.action_search)
        search_view.setMenuItem(item)

        return true
    }
}

