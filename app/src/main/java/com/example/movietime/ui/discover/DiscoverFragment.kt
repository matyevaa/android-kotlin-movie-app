package com.example.movietime.ui.discover

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movietime.R
import com.example.movietime.api.MovieDB
import com.example.movietime.data.AppDatabase
import com.example.movietime.data.BookmarkedMovie
import com.example.movietime.data.Movie
import com.example.movietime.databinding.FragmentDiscoverBinding
import com.example.movietime.ui.home.MovieConst
import com.example.movietime.ui.home.MovieListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.progressindicator.CircularProgressIndicator

class DiscoverFragment : Fragment() {

    private var _binding: FragmentDiscoverBinding? = null
    private val movieAdapter = MovieListAdapter(::onMovieItemClick)
    private lateinit var fab: FloatingActionButton
    private var isList:Boolean = true
    private lateinit var searchResultsListRV: RecyclerView
    private lateinit var popularResultsListRV: RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        isList = sharedPrefs.getBoolean(getString(R.string.listview), true)
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val searchBoxET: EditText = root.findViewById(R.id.et_search_box)
        val searchBtn: Button = root.findViewById(R.id.btn_search)

        searchResultsListRV = root.findViewById<RecyclerView>(R.id.rv_search_results)
        popularResultsListRV = root.findViewById<RecyclerView>(R.id.rv_popular_results)
        fab = root.findViewById(R.id.ab_sort)

        setView(root)

        val api = MovieDB(requireContext())
        searchBtn.setOnClickListener {
            val query = searchBoxET.text.toString()
            if(!TextUtils.isEmpty(query)) {
                api.doMovieSearch(query, movieAdapter)
                searchResultsListRV.scrollToPosition(0)
            }
        }

        api.popularMovies(movieAdapter)

        fab.setOnClickListener {
            Log.d(tag,"Click")
            with (sharedPrefs.edit()) {
                putBoolean(getString(com.example.movietime.R.string.listview), !isList)
                isList = !isList
                commit()
            }
            setView(root)
        }

        return root
    }

    private fun setView(root: View){
        if (isList){
            searchResultsListRV.layoutManager = LinearLayoutManager(root.context)
            popularResultsListRV.layoutManager = LinearLayoutManager(root.context)
            movieAdapter.setViewType(MovieConst.list_item)
            fab.setImageResource(R.drawable.ic_baseline_view_column_24)
        }else{
            searchResultsListRV.layoutManager = GridLayoutManager(root.context, 2)
            popularResultsListRV.layoutManager = GridLayoutManager(root.context, 2)
            movieAdapter.setViewType(MovieConst.tile_item)

            fab.setImageResource(R.drawable.ic_baseline_view_list_24)
        }
        searchResultsListRV.adapter = movieAdapter
        popularResultsListRV.adapter = movieAdapter
    }

    private fun onMovieItemClick(movie: Movie) {
        val directions = DiscoverFragmentDirections.navDetail(movie)
        findNavController().navigate(directions)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}