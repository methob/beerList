package com.br.beerlist.beerlist.fragments


import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import com.br.beerlist.beerlist.utils.BuildConfigApp
import com.br.beerlist.beerlist.R
import com.br.beerlist.beerlist.activities.BeerDetailActivity
import com.br.beerlist.beerlist.activities.ContainerBeerActivity
import com.br.beerlist.beerlist.adapters.ListBeersAdapter
import com.br.beerlist.beerlist.models.Beer
import com.br.beerlist.beerlist.services.BeerService
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_list_beer.*
import kotlinx.android.synthetic.main.fragment_search_beer.view.*


class SearchBeerFragment : BaseFragment<ContainerBeerActivity>(), BeerService.IListenerResponseBeers, MaterialSearchView.OnQueryTextListener {

    lateinit var service: BeerService
    var fragmentView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        if (this.fragmentView == null) { // Se já tiver passado por essa tela antes, aproveite a view retornada
            val view : View =  inflater.inflate(R.layout.fragment_search_beer, container, false)
            this.fragmentView = view
        }

        service = BeerService()

        activity!!.search_view.setOnQueryTextListener(this)

        setHasOptionsMenu(true)

        return fragmentView
    }

    fun setClickRecyclerView(adapter : ListBeersAdapter) {

        adapter.clickEvent.subscribe({ // Click da lista
            beer: Beer? ->
                val intent = Intent(activity, BeerDetailActivity::class.java)
                intent.putExtra(BuildConfigApp.FRAGMENT_PARAM_BEER, beer)
                startActivity(intent)
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        if (query == null || query.isEmpty()) { // Teste for Vazio não deixar pesquisar

            Toast.makeText(context, getString(R.string.txt_serch_empty), Toast.LENGTH_LONG).show()

        } else {

            fragmentView!!.progress_bar.visibility = View.VISIBLE
            fragmentView!!.view_no_results_found.visibility = View.INVISIBLE

            service.getBeersByName(query, this@SearchBeerFragment) // Fazer Request de bebidas
        }

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onResponseSuccessfully(beers: List<Beer>) { // Sucesso no Request, Preencher Lista com beers

        fragmentView!!.progress_bar.visibility = View.GONE

        if (beers.isEmpty())
            fragmentView!!.view_no_results_found.visibility = View.VISIBLE

        val adapter = ListBeersAdapter(beers, activity!!)
        fragmentView!!.recycler_view.adapter = adapter
        setClickRecyclerView(adapter)
    }

    override fun onResponseFailed(message: String) { // Falha no Request

        fragmentView!!.progress_bar.visibility = View.GONE
        fragmentView!!.view_no_results_found.visibility = View.VISIBLE

        if (isAdded) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) { // Preencher lupa de pesquisa
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.menu_search, menu)

        val item = menu?.findItem(R.id.action_search)
        activity!!.search_view.setMenuItem(item)
    }

}
