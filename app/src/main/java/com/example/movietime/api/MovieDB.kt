package com.example.movietime.api

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.movietime.MainActivity
import com.example.movietime.R
import com.example.movietime.data.Movie
import com.example.movietime.ui.home.MovieListAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MovieDB(context: Context) {
    private val apiBaseUrl = "https://api.themoviedb.org/3"
    private val apiKey = "c281ffcb75795819837f8b2643521195"

    private val requestQueue: RequestQueue = Volley.newRequestQueue(context)
    private val _status = MutableLiveData(Status.Waiting)
    val loadingStatus: LiveData<Status> = _status
    private val _searchResults = MutableLiveData<List<Movie>?>(null)
    val searchResults: LiveData<List<Movie>?> = _searchResults


    fun doMovieSearch(q: String, movieAdapter: MovieListAdapter) {
        //TODO refactoring kind broke these, maybe pass int View
//        val loadingIndicator = view.findViewById(R.id.loading_indicator)
//        val searchResultsListRV = findViewById(R.id.rv_search_results)
//        val searchErrorTV = findViewById(R.id.tv_search_error)

        Log.d("movies search function", q)
        val url = "$apiBaseUrl/search/movie?api_key=$apiKey&query=$q&page=1"
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        val jsonAdapter: JsonAdapter<MovieResults> =
            moshi.adapter(MovieResults::class.java)

        val req = StringRequest(
            Request.Method.GET,
            url,
            {
                Log.d("results", it)
                val results = jsonAdapter.fromJson(it)
                Log.d("movie result", results.toString())
                movieAdapter.updateMovieList(results?.results)
//                loadingIndicator.visibility = View.INVISIBLE
//                searchResultsListRV.visibility = View.VISIBLE
            },
            {
//                loadingIndicator.visibility = View.INVISIBLE
//                searchErrorTV.visibility = View.VISIBLE
            }
        )
//        loadingIndicator.visibility = View.VISIBLE
//        searchResultsListRV.visibility = View.INVISIBLE
//        searchErrorTV.visibility = View.INVISIBLE
        requestQueue.add(req)
    }

    fun popularMovies(movieAdapter: MovieListAdapter) {
        Log.d("popularMovies", "discover popular movies" )
        val url = "$apiBaseUrl/movie/popular?api_key=$apiKey&page=1"
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        val jsonAdapter: JsonAdapter<MovieResults> =
            moshi.adapter(MovieResults::class.java)

        val req = StringRequest(
            Request.Method.GET,
            url,
            {
                Log.d("popular results", it)
                val results = jsonAdapter.fromJson(it)
                Log.d("movie popular result", results.toString())
                movieAdapter.updateMovieList(results?.results)
                //loadingIndicator.visibility = View.INVISIBLE
                //popularResultsListRV.visibility = View.VISIBLE
            },
            {
                //loadingIndicator.visibility = View.INVISIBLE
                //searchErrorTV.visibility = View.VISIBLE
            }
        )
        requestQueue.add(req)
    }

    private data class MovieResults(
        val results: List<Movie>
    )
}