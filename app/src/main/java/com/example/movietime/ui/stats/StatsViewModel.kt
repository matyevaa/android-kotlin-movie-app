package com.example.movietime.ui.stats

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.movietime.data.AppDatabase
import com.example.movietime.data.BookmarkedMovie

class StatsViewModel(application: Application): AndroidViewModel(application) {
    private val detailedMovie = BookmarkedMovie(
        AppDatabase.getInstance(application).MovieDao()
    )

}