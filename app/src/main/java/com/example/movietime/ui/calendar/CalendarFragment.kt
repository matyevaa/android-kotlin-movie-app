package com.example.movietime.ui.calendar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movietime.R
import com.example.movietime.data.DetailedMovie
import com.example.movietime.data.Movie
import com.example.movietime.data.toMovieList
import com.example.movietime.databinding.FragmentLibraryBinding
import com.example.movietime.ui.BookmarkedMovieViewModel
import com.example.movietime.ui.discover.DiscoverFragment
import com.example.movietime.ui.home.MovieConst
import com.example.movietime.ui.home.MovieListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class CalendarFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private val movieAdapter = MovieListAdapter(::onMovieItemClick)
    private val dbModel: BookmarkedMovieViewModel by viewModels()
    private lateinit var searchResultsListRV: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var isList:Boolean = true

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        searchResultsListRV = root.findViewById(R.id.rv_library_list)
        fab = root.findViewById(R.id.ab_sort)

        setView(root)

        dbModel.RecentMovies.observe(viewLifecycleOwner){movies ->
            when (movies) {
                null -> {
                    Log.d(tag,"EMPTY")
                }
                else -> {
                    movieAdapter.updateMovieList(movies.toMovieList())
                }
            }
        }

        fab.setOnClickListener { setView(root) }

        return root
    }

    private fun setView(root: View){ //TODO add some kind of animation
        if (isList){
            searchResultsListRV.layoutManager = LinearLayoutManager(root.context)
            movieAdapter.setViewType(MovieConst.list_item)
            isList = false
            fab.setImageResource(R.drawable.ic_baseline_view_column_24)
        }else{
            searchResultsListRV.layoutManager = GridLayoutManager(root.context, 2)
            movieAdapter.setViewType(MovieConst.tile_item)
            isList = true
            fab.setImageResource(R.drawable.ic_baseline_view_list_24)
        }
        searchResultsListRV.adapter = movieAdapter
    }

    private fun onMovieItemClick(movie: Movie) {
        Log.d("LibraryFragment", movie.toString())
        val directions = CalendarFragmentDirections.navDetail(movie)
        findNavController().navigate(directions)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}