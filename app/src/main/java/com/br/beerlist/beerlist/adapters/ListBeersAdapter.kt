package com.br.beerlist.beerlist.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.br.beerlist.beerlist.R
import com.br.beerlist.beerlist.models.Beer
import com.bumptech.glide.Glide
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_view_beer.view.*


class ListBeersAdapter(private val beers: List<Beer>, private val context:Context) : Adapter<ListBeersAdapter.ViewHolder>() {

    val clickSubject = PublishSubject.create<Beer>()!!
    var clickEvent : Observable<Beer>

    init {
        clickEvent = clickSubject
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view_beer, parent, false))
    }

    override fun getItemCount(): Int {
        return beers.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        val beer = beers[position]
        holder?.bindView(beer)

        holder?.itemView?.setOnClickListener {
            clickSubject.onNext(beer)
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(beer: Beer) {

            val name = itemView.txt_name
            val description = itemView.txt_description

            Glide.with(context).load(beer.imageUrl).into(itemView.img_beer)

            name.text = beer.name
            description.text = beer.tagline
        }
    }
}