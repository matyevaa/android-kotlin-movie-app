package com.example.movietime.ui.library

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


class LibraryFragment : Fragment() {

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
        // Mock DB data
//        dbModel.addDetailedMovie(DetailedMovie(id=634649, title="Spider-Man: No Way Home", original_title="Spider-Man: No Way Home", overview="Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.", popularity=5083.954, release_date="2021-12-15", poster_path="/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg", backdrop_path="/iQFcwSGbZXMkeyKrxbPnwnRo5fl.jpg", budget=200000000, status="Released", runtime=148))
//        dbModel.addDetailedMovie(DetailedMovie(id=414906, title="The Batman", original_title="The Batman", overview="In his second year of fighting crime, Batman uncovers corruption in Gotham City that connects to his own family while facing a serial killer known as the Riddler.", popularity=3827.658, release_date="2022-03-01", poster_path="/74xTEgt7R36Fpooo50r9T25onhq.jpg", backdrop_path="/5P8SmMzSNYikXpxil6BYzJ16611.jpg", budget=185000000, status="Released", runtime=176))
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        searchResultsListRV = root.findViewById(R.id.rv_library_list)
        fab = root.findViewById(R.id.ab_sort)

        setView(root)

        dbModel.bookmarkedMovies.observe(viewLifecycleOwner){movies ->
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
        val directions = LibraryFragmentDirections.navDetail(movie)
        findNavController().navigate(directions)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}