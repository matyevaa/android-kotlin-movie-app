package com.example.movietime.ui.stats

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import com.example.movietime.data.AppDatabase
import com.example.movietime.data.BookmarkedMovie

class StatsViewModel(application: Application): AndroidViewModel(application) {
    private val detailedMovie = BookmarkedMovie(
        AppDatabase.getInstance(application).MovieDao()
    )

    val longestRunTimeMovie = detailedMovie.getLongestRuntimeMovie().asLiveData()
    val highestBudgetMovie = detailedMovie.getHighestBudgetMovie().asLiveData()
    val totalRunTime = detailedMovie.getTotalRunTime().asLiveData()
    val mostRecentReleasedMovie = detailedMovie.getMostRecentlyReleasedMovie().asLiveData()
    val avgRuntime = detailedMovie.getAvgRunTime().asLiveData()

}