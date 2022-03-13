package com.example.movietime.work

import android.app.PendingIntent
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf
import androidx.navigation.NavDeepLinkBuilder
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.movietime.R
import com.example.movietime.data.*

class MovieWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params){

    private val bookmarkedMovie = BookmarkedMovie(
        AppDatabase.getInstance(context).MovieDao()
    )


    override suspend fun doWork(): Result {

        val upcomingMovies =bookmarkedMovie.getRecentMovieOnce()

        Log.d("Notification",upcomingMovies.toString())
        for (upcomingMovie in upcomingMovies){
            sendNotification(upcomingMovie)
            Log.d("Notification","Notification triggered")
        }

        return Result.success()
    }

    private fun sendNotification(movie: DetailedMovie){

        val pendingIntent  =NavDeepLinkBuilder(applicationContext)
            .setGraph(R.navigation.mobile_navigation)
            .setDestination(R.id.movie_detail)
            .setArguments(bundleOf(
                "movie" to movie.toMovie()
            ))
            .createPendingIntent()

        Log.d("Not",movie.title)
        val builder = NotificationCompat.Builder(
            applicationContext,
            applicationContext.getString(R.string.notification_stars_channel))
        builder.setSmallIcon(R.drawable.baseline_theaters_24)
            .setContentTitle(applicationContext.getString(R.string.notification_stars_title))
            .setContentText(applicationContext.getString(R.string.notification_stars_text, movie.original_title))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        NotificationManagerCompat.from(applicationContext)
            .notify(movie.original_title.hashCode(),builder.build())
    }

}