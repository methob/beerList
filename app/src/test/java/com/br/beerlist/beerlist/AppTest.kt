package com.br.beerlist.beerlist

import android.content.Context
import com.br.beerlist.beerlist.activities.ContainerBeerActivity
import com.br.beerlist.beerlist.fragments.SearchBeerFragment
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_list_beer.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AppTest {

    @Mock
    var context: Context? = null

    var searchViewMock: SearchBeerFragment = Mockito.mock(SearchBeerFragment::class.java)

    @Test
    fun emptySearch() {

        val activity = searchViewMock.activity as ContainerBeerActivity

        activity.search_view.setOnQueryTextListener(object: MaterialSearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    if (query.isEmpty()) {
                        assert(false)
                    }
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }
}