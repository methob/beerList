package com.br.beerlist.beerlist

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.view_search_toolbar.*
import kotlinx.android.synthetic.main.view_toolbar.*

class ListBeerActivity : AppCompatActivity() {

    lateinit var searchMenu: Menu
    lateinit var itemSearch: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_beer)

        setSupportActionBar(toolbar)
        setSearchtoolbar()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        when (item.itemId) {
            R.id.action_search -> {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    circleReveal(R.id.search_toolbar, 1, true, true)
                else
                    search_toolbar.visibility = View.VISIBLE

                itemSearch.expandActionView()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("PrivateResource")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    fun circleReveal(viewID: Int, posFromRight: Int, containsOverflow: Boolean, isShow: Boolean) {
        val myView = findViewById<View>(viewID)

        var width = myView.width

        if (posFromRight > 0)
            width -= posFromRight * resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_material) - resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_material) / 2
        if (containsOverflow)
            width -= resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_overflow_material)

        val cx = width
        val cy = myView.height / 2

        val anim: Animator
        if (isShow)
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0f, width.toFloat())
        else
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, width.toFloat(), 0f)

        anim.duration = 220.toLong()

        // make the view invisible when the animation is done
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                if (!isShow) {
                    super.onAnimationEnd(animation)
                    myView.visibility = View.INVISIBLE
                }
            }
        })

        // make the view visible and start the animation
        if (isShow)
            myView.visibility = View.VISIBLE

        // start the animation
        anim.start()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    fun initSearchView() {

        val searchView = searchMenu.findItem(R.id.action_filter_search).actionView as android.support.v7.widget.SearchView

        // Enable/Disable Submit button in the keyboard

        searchView.isSubmitButtonEnabled = false

        // Change search close button image

        val closeButton = searchView.findViewById<ImageView>(R.id.search_close_btn) as ImageView
        closeButton.setImageResource(R.drawable.ic_close_black)

        // set hint and the text colors

        val txtSearch = searchView.findViewById<EditText>(android.support.v7.appcompat.R.id.search_src_text) as EditText
        txtSearch.hint = "Search.."
        txtSearch.setHintTextColor(Color.DKGRAY)
        txtSearch.setTextColor(resources.getColor(R.color.colorPrimary))


        // set the cursor

        val searchTextView = searchView.findViewById<AutoCompleteTextView>(android.support.v7.appcompat.R.id.search_src_text) as AutoCompleteTextView
        try {
            val mCursorDrawableRes = TextView::class.java.getDeclaredField("mCursorDrawableRes")
            mCursorDrawableRes.isAccessible = true
            mCursorDrawableRes.set(searchTextView, R.drawable.search_cursor) //This sets the cursor resource ID to 0 or @null which will make it visible on white background
        } catch (e: Exception) {
            e.printStackTrace()
        }


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                callSearch(query)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                callSearch(newText)
                return true
            }

            fun callSearch(query: String) {
                //Do searching
                Log.i("query", "" + query)

            }

        })

    }

    fun setSearchtoolbar() {

        search_toolbar.inflateMenu(R.menu.menu_search)
        searchMenu = search_toolbar.menu

        search_toolbar.setNavigationOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                circleReveal(R.id.search_toolbar, 1, true, false)
            } else {
                search_toolbar.visibility = View.GONE
            }
        }

        itemSearch = searchMenu.findItem(R.id.action_filter_search)

        MenuItemCompat.setOnActionExpandListener(itemSearch, object : MenuItemCompat.OnActionExpandListener {
            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                // Do something when collapsed
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    circleReveal(R.id.search_toolbar, 1, true, false)
                } else
                    search_toolbar.visibility = View.GONE

                return true
            }

            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                // Do something when expanded
                return true
            }
        })

        initSearchView()

    }
}

