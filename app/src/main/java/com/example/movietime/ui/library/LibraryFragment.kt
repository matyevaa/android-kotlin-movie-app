package com.example.movietime.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movietime.R
import com.example.movietime.data.Movie
import com.example.movietime.databinding.FragmentLibraryBinding
import com.example.movietime.ui.home.MovieConst
import com.example.movietime.ui.home.MovieListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private val movieAdapter = MovieListAdapter()
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
        movieAdapter.updateMovieList(listOf(Movie("","","Shrek is an anti-social and highly-territorial green ogre who loves the solitude of his swamp. His life is interrupted after the dwarfish Lord Farquaad of Duloc unknowingly exiles a vast number of fairy-tale creatures to Shrek's swamp.",93.0,"2000","Shrek"),
            Movie("","","Shrek is an anti-social and highly-territorial green ogre who loves the solitude of his swamp. His life is interrupted after the dwarfish Lord Farquaad of Duloc unknowingly exiles a vast number of fairy-tale creatures to Shrek's swamp.",93.0,"2000","Shrek"),
            Movie("","","Shrek is an anti-social and highly-territorial green ogre who loves the solitude of his swamp. His life is interrupted after the dwarfish Lord Farquaad of Duloc unknowingly exiles a vast number of fairy-tale creatures to Shrek's swamp.",93.0,"2000","Shrek")))

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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}