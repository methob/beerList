package com.br.beerlist.beerlist.fragments


import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.br.beerlist.beerlist.BuildConfigApp
import com.br.beerlist.beerlist.R
import com.br.beerlist.beerlist.activities.BeerDetailActivity
import com.br.beerlist.beerlist.activities.ContainerBeerActivity
import com.br.beerlist.beerlist.adapters.ListBeersAdapter
import com.br.beerlist.beerlist.models.Beer
import com.br.beerlist.beerlist.services.BeerService
import kotlinx.android.synthetic.main.fragment_favorites.view.*


/**
 * A simple [Fragment] subclass.
 */
class FavoritesFragment : BaseFragment<ContainerBeerActivity>() {

    lateinit var favoriteView: View
    lateinit var service: BeerService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        service = BeerService()
        favoriteView = inflater.inflate(R.layout.fragment_favorites, container, false)

        return favoriteView
    }

    fun setClickRecyclerView(adapter: ListBeersAdapter) {

        adapter.clickEvent.subscribe({
            beer: Beer? ->
            val intent = Intent(activity, BeerDetailActivity::class.java)
            intent.putExtra(BuildConfigApp.FRAGMENT_PARAM_BEER, beer)
            startActivity(intent)
        })
    }

    override fun onStart() {
        super.onStart()

        val beers: List<Beer> = service.database.findAll(Beer::class.java)

        favoriteView.rel_favorite_not_beers_found.visibility = if (beers.isEmpty())  View.VISIBLE else View.INVISIBLE

        val adapter = ListBeersAdapter(beers, activity!!)
        favoriteView.recycler_view_favorites.adapter = adapter
        setClickRecyclerView(adapter)
    }

}
