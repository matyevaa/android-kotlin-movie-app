package com.example.movietime.ui.detail
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movietime.R
import com.example.movietime.api.MovieDB
import com.example.movietime.data.*
import com.example.movietime.databinding.FragmentDetailedBinding
import com.example.movietime.ui.BookmarkedMovieViewModel
import com.example.movietime.ui.library.LibraryViewModel



class MovieDetailFragment : Fragment(R.layout.fragment_detailed) {
    private var _binding: FragmentDetailedBinding? = null
    private val args:MovieDetailFragmentArgs by navArgs()
    private val dbModel: BookmarkedMovieViewModel by viewModels()

    private var movie = MutableLiveData<DetailedMovie?>(null)

    private var isBookmarked = false
    private val viewModel:BookmarkedMovieViewModel by viewModels()

    private val binding get() = _binding!!

    fun updateMovie(newMovie: DetailedMovie?) {
        movie.value = newMovie!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val api = MovieDB(requireContext())
        api.getMovie(args.movie.id.toString(), this)

        movie.observe(viewLifecycleOwner,) {
            if (it != null) {
                updateView(view)
                //dbModel.addDetailedMovie(movie.value!!)// Used for quickly loading mock data ot DB
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.detail_menu, menu)
        val bookmarkItem = menu.findItem(R.id.action_bookmark)


        viewModel.getMovieByName(args.movie.title).observe(viewLifecycleOwner) { bookmarkedRepo ->
            when (bookmarkedRepo) {
                null -> {
                    Log.d("hi", "NOt in DB")
                    isBookmarked = false
                    bookmarkItem.isChecked = false
                    bookmarkItem.icon = AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.ic_baseline_bookmark_border_24
                    )
                }
                else -> {
                    Log.d("hi", "in DB")
                    isBookmarked = true
                    bookmarkItem.isChecked = true
                    bookmarkItem.icon = AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.ic_baseline_bookmark_24
                    )
                }
            }
        }
    }
    private fun updateView(view: View){
        view.findViewById<TextView>(R.id.tv_title_detail).text = movie.value?.title
        view.findViewById<TextView>(R.id.tv_overview_detail).text = movie.value?.overview
        view.findViewById<TextView>(R.id.tv_release_year).text = getString(R.string.date_year_format, movie.value?.date())
        view.findViewById<TextView>(R.id.tv_avg_rating_detail).text = movie.value?.vote_average.toString()
        view.findViewById<TextView>(R.id.tv_budget).text = getString(R.string.budget_format, movie.value?.budget)
        view.findViewById<TextView>(R.id.tv_status).text = movie.value?.status
        view.findViewById<TextView>(R.id.tv_runtime).text = getString(R.string.runtime_format, movie.value?.runtime)
        view.findViewById<TextView>(R.id.tv_genre_detail).text = movie.value?.genres?.toNameList().toString()
        view.findViewById<TextView>(R.id.tv_release_date_detail).text = getString(R.string.date_format, movie.value?.date())

        Glide.with(view)
            .load(API_Const.img_base_url+movie.value?.poster_path)
            .into(view.findViewById(R.id.iv_poster_detail))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun toggleRepoBookmark(menuItem: MenuItem) {

        isBookmarked = !isBookmarked
        when (isBookmarked) {
            true -> {
                viewModel.addDetailedMovie(movie.value!!)
            }
            false -> {
                viewModel.deleteDetailedMovie(movie.value!!)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {

            R.id.action_bookmark -> {
                toggleRepoBookmark(item)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
//Taesoo: I need this just to check whether the data will be saved into DB or not
//
