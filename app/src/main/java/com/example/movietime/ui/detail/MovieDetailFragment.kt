package com.example.movietime.ui.detail
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movietime.R
import com.example.movietime.api.MovieDB
import com.example.movietime.data.API_Const
import com.example.movietime.data.DetailedMovie
import com.example.movietime.databinding.FragmentDetailedBinding
import com.example.movietime.ui.calendar.CalendarViewModel
import com.example.movietime.ui.home.MovieListAdapter
import com.example.movietime.ui.library.LibraryViewModel



class MovieDetailFragment : Fragment(R.layout.fragment_detailed) {
    private var _binding: FragmentDetailedBinding? = null
    private val args:MovieDetailFragmentArgs by navArgs()

    private var movie = MutableLiveData<DetailedMovie?>(null)

    private var isBookmarked = false
    private val viewModel:LibraryViewModel by viewModels()

    private val binding get() = _binding!!

    fun updateMovie(newMovie: DetailedMovie?) {
        movie.value = newMovie!!
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val api = MovieDB(requireContext())
        api.getMovie(args.movie.id.toString(), this)

        movie.observe(viewLifecycleOwner, Observer {
            if(it != null){
                updateView(view) 
            }
        })

    }
    
    private fun updateView(view: View){
        view.findViewById<TextView>(R.id.tv_title_detail).text = movie.value?.title
        view.findViewById<TextView>(R.id.tv_overview_detail).text = movie.value?.overview
        view.findViewById<TextView>(R.id.tv_release_date_detail).text = movie.value?.release_date
        view.findViewById<TextView>(R.id.tv_popularity_detail).text = movie.value?.popularity.toString()
        view.findViewById<TextView>(R.id.tv_budget).text = movie.value?.budget.toString()
        view.findViewById<TextView>(R.id.tv_status).text = movie.value?.status
        view.findViewById<TextView>(R.id.tv_runtime).text = movie.value?.runtime.toString()
        view.findViewById<TextView>(R.id.tv_genre_detail).text = "TODO"

        Glide.with(view)
            .load(API_Const.img_base_url+movie.value?.poster_path)
            .into(view.findViewById(R.id.iv_poster_detail))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

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
