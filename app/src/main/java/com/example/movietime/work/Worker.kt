package com.example.movietime.work

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.movietime.R
import com.example.movietime.data.AppDatabase
import com.example.movietime.data.BookmarkedMovie
import com.example.movietime.data.DetailedMovie

class Worker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params){

    private val bookmarkedMovie = BookmarkedMovie(
        AppDatabase.getInstance(context).MovieDao()
    )


    override suspend fun doWork(): Result {

        val upcomingMovies =bookmarkedMovie.getRecentMovieOnce()
        for (upcomingMovie in upcomingMovies){
            sendNotification(upcomingMovie)
        }

        return Result.success()
    }

    private fun sendNotification(movie: DetailedMovie){
        val builder = NotificationCompat.Builder(
            applicationContext,
            applicationContext.getString(R.string.notification_stars_channel))
        builder.setSmallIcon(R.drawable.baseline_theaters_24)
            .setContentTitle(applicationContext.getString(R.string.notification_stars_title, movie.original_title))
            .setContentText(applicationContext.getString(R.string.notification_stars_text, movie.original_title))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        NotificationManagerCompat.from(applicationContext)
            .notify(movie.original_title.hashCode(),builder.build())


    }

}