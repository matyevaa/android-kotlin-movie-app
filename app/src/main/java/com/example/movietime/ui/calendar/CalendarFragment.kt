package com.example.movietime.ui.calendar

import android.app.PendingIntent
import android.content.Intent

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager

import android.util.Log
import android.view.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.edit
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.movietime.MainActivity
import com.example.movietime.R
import com.example.movietime.data.*
import com.example.movietime.databinding.FragmentLibraryBinding
import com.example.movietime.ui.BookmarkedMovieViewModel
import com.example.movietime.ui.home.MovieConst
import com.example.movietime.ui.home.MovieListAdapter
import com.example.movietime.work.MovieWorker
import com.google.android.material.floatingactionbutton.FloatingActionButton



class CalendarFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private val movieAdapter = MovieListAdapter(::onMovieItemClick)
    private val dbModel: BookmarkedMovieViewModel by viewModels()
    private lateinit var searchResultsListRV: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var sharedPrefs: SharedPreferences
    private var isList:Boolean = true

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        isList = sharedPrefs.getBoolean(getString(R.string.listview), true)

        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        searchResultsListRV = root.findViewById(R.id.rv_library_list)
        fab = root.findViewById(R.id.ab_sort)

        setView(root)

        dbModel.RecentMovie.observe(viewLifecycleOwner){movies ->
            when (movies) {
                null -> {
                    Log.d(tag,"EMPTY")
                }
                else -> {
                    Log.d("RECENT",movies.toString())
                    movieAdapter.updateMovieList(movies.toMovieList())
                }
            }
        }

        fab.setOnClickListener {
            Log.d(tag,"Click")
            with (sharedPrefs.edit()) {
                putBoolean(getString(R.string.listview), !isList)
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
            movieAdapter.setViewType(MovieConst.list_item)
            fab.setImageResource(R.drawable.ic_baseline_view_column_24)
        }else{
            searchResultsListRV.layoutManager = GridLayoutManager(root.context, 2)
            movieAdapter.setViewType(MovieConst.tile_item)
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