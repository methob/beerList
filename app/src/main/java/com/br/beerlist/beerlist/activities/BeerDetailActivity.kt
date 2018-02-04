package com.br.beerlist.beerlist.activities

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.br.beerlist.beerlist.utils.BuildConfigApp
import com.br.beerlist.beerlist.R
import com.br.beerlist.beerlist.models.Beer
import com.br.beerlist.beerlist.services.BeerService
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_beer_detail.*

/* Activity de Detalhe da lista de bebidas*/
class BeerDetailActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var beer: Beer
    lateinit var beerService: BeerService // serviço que permite que se acesse o bando de dados
    var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer_detail)

        beer = intent.getParcelableExtra<Beer>(BuildConfigApp.FRAGMENT_PARAM_BEER)

        beerService = BeerService()

        val tempBeer = beerService.getBeerByIDFromDatabase(beer.id) // Obtem a  e bebidas do banco

        if (tempBeer != null) {

            this.beer = tempBeer // Seta o beer atual para ser um beer recebido do banco e não do webService, para que se possa fazer operações sobre o mesmo.

            ic_heart.setImageResource(R.drawable.ic_favorite) // Se for favorito troca imagem do coração
            ic_heart.drawable.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
            isFavorite = true
        }

        txt_name.text = beer.name
        txt_tagline.text = beer.tagline
        txt_description.text = beer.description

        Glide.with(this).load(beer.imageUrl).into(img_detail_bear)

        beerService = BeerService()

        ic_heart.setOnClickListener(this)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun onClick(v: View?) {

        isFavorite = !isFavorite

        /* Lógica para mudar image do coração ao se favoritar */
        if (isFavorite) {
            ic_heart.setImageResource(R.drawable.ic_favorite)
            ic_heart.drawable.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        } else {

            ic_heart.setImageResource(R.drawable.ic_favorite_border)
            ic_heart.drawable.setColorFilter(ContextCompat.getColor(this, android.R.color.darker_gray), PorterDuff.Mode.SRC_IN)
        }
    }

    override fun onPause() {
        super.onPause()

        if (isFinishing) { // Toda vez que a activity estiver fechando, salve as atualizações

            if (isFavorite) {
                beerService.database.add(beer)
            } else {
                beerService.database.remove(beer)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // infla a seta de voltar
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

}
