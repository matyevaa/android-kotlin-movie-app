package com.example.movietime.ui.library

import android.app.Application
import androidx.lifecycle.*
import com.example.movietime.data.AppDatabase
import com.example.movietime.data.BookmarkedMovie
import com.example.movietime.data.DetailedMovie
import kotlinx.coroutines.launch

class LibraryViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is library Fragment"
    }
    val text: LiveData<String> = _text

    private val mov = BookmarkedMovie(
        AppDatabase.getInstance(application).MovieDao()
    )

    fun addBookmarkedMovie(movie: DetailedMovie) {
        viewModelScope.launch {
            mov.insertMovie(movie)
        }
    }
    fun removeBookmarkedMovie(movie: DetailedMovie) {
        viewModelScope.launch {
            mov.deleteMovie(movie)
        }
    }


}