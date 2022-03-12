package com.example.movietime.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.movietime.data.AppDatabase
import com.example.movietime.data.BookmarkedMovie
import com.example.movietime.data.DetailedMovie
import kotlinx.coroutines.launch

class BookmarkedMovieViewModel(application: Application): AndroidViewModel(application) {

    private val detailedMovie = BookmarkedMovie(
        AppDatabase.getInstance(application).MovieDao()
    )

    val bookmarkedMovies = detailedMovie.getAllDetailedMovie().asLiveData()

    fun addDetailedMovie(movie: DetailedMovie){
        viewModelScope.launch {
            detailedMovie.insertMovie(movie)
        }

    }

    fun deleteDetailedMovie(movie:DetailedMovie){
        viewModelScope.launch {
            detailedMovie.deleteMovie(movie)
        }
    }

    val RecentMovies = detailedMovie.getRecentMovie().asLiveData()

}