package com.br.beerlist.beerlist.activity

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.br.beerlist.beerlist.activities.ContainerBeerActivity
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_list_beer.*
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestContainerActivity {

    @Rule
    @JvmField
    var rule: ActivityTestRule<ContainerBeerActivity> = ActivityTestRule(ContainerBeerActivity::class.java)

    lateinit var activity: ContainerBeerActivity

    @Before
    fun initClass() {

        activity = rule.activity
    }

    @Throws
    @Test
    fun testSearchView() {

        val searchView: MaterialSearchView = activity.search_view

        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                Assert.assertTrue(query != null && query.isEmpty())

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

        })

    }
}