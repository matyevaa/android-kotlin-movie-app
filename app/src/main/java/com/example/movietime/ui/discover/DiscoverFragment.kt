package com.example.movietime.ui.discover

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movietime.R
import com.example.movietime.api.MovieDB
import com.example.movietime.databinding.ActivityMainBinding
import com.example.movietime.databinding.FragmentDiscoverBinding
import com.example.movietime.ui.home.MovieListAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.progressindicator.CircularProgressIndicator

class DiscoverFragment : Fragment() {

    private var _binding: FragmentDiscoverBinding? = null
    private val movieAdapter = MovieListAdapter()

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
                movieAdapter.updateMovieList(api.searchResults.value)
                //doMovieSearch(query)
                searchResultsListRV.scrollToPosition(0)
            }
        }

        //popularMovies()
        api.popularMovies(movieAdapter)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}