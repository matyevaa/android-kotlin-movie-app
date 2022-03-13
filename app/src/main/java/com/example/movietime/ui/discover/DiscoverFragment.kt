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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movietime.R
import com.example.movietime.api.MovieDB
import com.example.movietime.data.AppDatabase
import com.example.movietime.data.BookmarkedMovie
import com.example.movietime.data.Movie
import com.example.movietime.databinding.FragmentDiscoverBinding
import com.example.movietime.ui.home.MovieListAdapter
import com.google.android.material.progressindicator.CircularProgressIndicator

class DiscoverFragment : Fragment() {

    private var _binding: FragmentDiscoverBinding? = null
    private val movieAdapter = MovieListAdapter(::onMovieItemClick)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val searchBoxET: EditText = root.findViewById(R.id.et_search_box)
        val searchBtn: Button = root.findViewById(R.id.btn_search)

        val searchErrorTV = root.findViewById<TextView>(R.id.tv_search_error)
        val loadingIndicator = root.findViewById<CircularProgressIndicator>(R.id.loading_indicator)

        val searchResultsListRV = root.findViewById<RecyclerView>(R.id.rv_search_results)
        searchResultsListRV.layoutManager = LinearLayoutManager(requireContext())
        searchResultsListRV.setHasFixedSize(true)

        searchResultsListRV.adapter = movieAdapter

        val popularResultsListRV = root.findViewById<RecyclerView>(R.id.rv_popular_results)
        popularResultsListRV.layoutManager = LinearLayoutManager(requireContext())
        popularResultsListRV.setHasFixedSize(true)

        popularResultsListRV.adapter = movieAdapter
        val api = MovieDB(requireContext())
        searchBtn.setOnClickListener {
            val query = searchBoxET.text.toString()
            if(!TextUtils.isEmpty(query)) {
                api.doMovieSearch(query, movieAdapter)
                searchResultsListRV.scrollToPosition(0)
            }
        }

        api.popularMovies(movieAdapter)

        return root
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