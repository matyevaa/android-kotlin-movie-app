package com.example.movietime.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movietime.R
import com.example.movietime.data.Movie
import com.example.movietime.databinding.FragmentLibraryBinding
import com.example.movietime.ui.home.MovieListAdapter

class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private val movieAdapter = MovieListAdapter()
    private lateinit var searchResultsListRV: RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(LibraryViewModel::class.java)

        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        searchResultsListRV = root.findViewById(R.id.rv_library_list)
        searchResultsListRV.layoutManager = LinearLayoutManager(root.context)
        searchResultsListRV.adapter = movieAdapter
        movieAdapter.updateMovieList(listOf(Movie("Shrek is an anti-social and highly-territorial green ogre who loves the solitude of his swamp. His life is interrupted after the dwarfish Lord Farquaad of Duloc unknowingly exiles a vast number of fairy-tale creatures to Shrek's swamp.",93.0,"2000","Shrek")))

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}