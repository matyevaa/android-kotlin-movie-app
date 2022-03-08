package com.example.movietime.ui.detail
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movietime.R
import com.example.movietime.data.API_Const
import com.example.movietime.data.Movie
import com.example.movietime.databinding.FragmentCalendarBinding
import com.example.movietime.databinding.FragmentDetailedBinding
import com.example.movietime.ui.calendar.CalendarViewModel
import com.example.movietime.ui.home.MovieListAdapter
import com.example.movietime.ui.library.LibraryViewModel


class MovieDetailFragment : Fragment(R.layout.fragment_activity_movie_detail) {
    //TAESOO:flag for bookmark
    private var isBookmarked = false
    //TAESOO:viewModel for
    private val viewModel:LibraryViewModel by viewModels()

    private var _binding: FragmentDetailedBinding? = null

    private val args:MovieDetailFragmentArgs by navArgs()

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        view.findViewById<TextView>(R.id.tv_title).text = args.movie.title
        view.findViewById<TextView>(R.id.tv_overview).text = args.movie.overview
        view.findViewById<TextView>(R.id.tv_release_date).text = args.movie.release_date
        view.findViewById<TextView>(R.id.tv_popularity).text = args.movie.popularity.toString()
        view.findViewById<TextView>(R.id.tv_genre).text = args.movie.genre_ids.toString()


        Log.d("MovieDetailedFragment", "args.movie: ${args.movie}")
        Log.d("MovieDetailedFragmentTitle", "args.movie: ${args.movie.title}")
        Log.d("MovieDetailedFragmentGen", "args.movie: ${args.movie.genre_ids}")
        Log.d("MovieDetailedFragmentDescr", "args.movie: ${args.movie.overview}")
        Log.d("MovieDetailedFragmentRelease", "args.movie: ${args.movie.release_date}")
        Log.d("MovieDetailedFragmentPopularity", "args.movie: ${args.movie.popularity}")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    //Taesoo: I need this just to check whether the data will be saved into DB or not
//    private fun toggleRepoBookmark(menuItem: MenuItem) {
//
//        isBookmarked = !isBookmarked
//        when (isBookmarked) {
//            true -> {
//                viewModel.addBookmarkedMovie(args.movie)
//            }
//            false -> {
//                viewModel.removeBookmarkedMovie(args.movie)
//            }
//        }
//    }

}