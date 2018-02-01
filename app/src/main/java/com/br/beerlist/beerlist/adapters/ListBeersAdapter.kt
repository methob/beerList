package com.br.beerlist.beerlist.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.br.beerlist.beerlist.R
import com.br.beerlist.beerlist.models.Beer
import kotlinx.android.synthetic.main.item_view_beer.view.*


class ListBeersAdapter(private val beers: List<Beer>, private val context:Context) : Adapter<ListBeersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view_beer, parent, false))
    }

    override fun getItemCount(): Int {
        return beers.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        val beer = beers[position]
        holder?.bindView(beer)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(beer: Beer) {

            val name = itemView.txt_name
            val description = itemView.txt_description

            name.text = beer.name
            description.text = beer.description
        }
    }
}