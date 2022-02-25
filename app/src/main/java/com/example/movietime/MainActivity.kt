package com.example.movietime

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.movietime.data.Movie
import com.example.movietime.databinding.ActivityMainBinding
import com.example.movietime.ui.home.MovieListAdapter
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainActivity : AppCompatActivity() {

    private val apiBaseUrl = "https://api.themoviedb.org/3"
    private val apiKey = "c281ffcb75795819837f8b2643521195"

    private lateinit var requestQueue: RequestQueue
    private lateinit var binding: ActivityMainBinding
    private val movieAdapter = MovieListAdapter()
    private lateinit var searchResultsListRV: RecyclerView
    private lateinit var searchErrorTV: TextView
    private lateinit var loadingIndicator: CircularProgressIndicator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestQueue = Volley.newRequestQueue(this)

        val searchBoxET: EditText = findViewById(R.id.et_search_box)
        var searchBtn: Button = findViewById(R.id.btn_search)

        searchErrorTV = findViewById(R.id.tv_search_error)
        loadingIndicator = findViewById(R.id.loading_indicator)

        searchResultsListRV = findViewById(R.id.rv_search_results)
        searchResultsListRV.layoutManager = LinearLayoutManager(this)
        searchResultsListRV.setHasFixedSize(true)

        searchResultsListRV.adapter = movieAdapter

        searchBtn.setOnClickListener {
            val query = searchBoxET.text.toString()
            if(!TextUtils.isEmpty(query)) {
                doMovieSearch(query)
                searchResultsListRV.scrollToPosition(0)
            }
        }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_discover, R.id.navigation_library, R.id.navigation_calendar
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun doMovieSearch(q: String) {
        Log.d("movies search function", q)
        val url = "$apiBaseUrl/search/movie?api_key=$apiKey&query=$q&page=1"
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        val jsonAdapter: JsonAdapter<MovieSearchResults> =
            moshi.adapter(MovieSearchResults::class.java)

        val req = StringRequest(
            Request.Method.GET,
            url,
            {
                Log.d("results", it)
                var results = jsonAdapter.fromJson(it)
                Log.d("movie result", results.toString())
                movieAdapter.updateMovieList(results?.results)
                loadingIndicator.visibility = View.INVISIBLE
                searchResultsListRV.visibility = View.VISIBLE
            },
            {
                loadingIndicator.visibility = View.INVISIBLE
                searchErrorTV.visibility = View.VISIBLE
            }
        )

        loadingIndicator.visibility = View.VISIBLE
        searchResultsListRV.visibility = View.INVISIBLE
        searchErrorTV.visibility = View.INVISIBLE
        requestQueue.add(req)
    }

    private data class MovieSearchResults(
        val results: List<Movie>
    )
}
