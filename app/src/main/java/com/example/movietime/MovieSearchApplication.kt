package com.example.movietime

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

import com.example.movietime.work.MovieWorker
import java.util.concurrent.TimeUnit

class MovieSearchApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        launchWorker()
        createMovieNotificationChannel()
    }

    private fun launchWorker(){
//        val constraints = Constraints.Builder()
//            .setRequiredNetworkType(NetworkType.CONNECTED)
//            .build()
//        val workRequest = PeriodicWorkRequestBuilder<Worker>(
//            12,
//            TimeUnit.HOURS
//        ).setConstraints(constraints).build()
//        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
//            "moviesearchWorker",
//            ExistingPeriodicWorkPolicy.REPLACE,
//            workRequest
//        )
        val workRequest = OneTimeWorkRequestBuilder<MovieWorker>().build()
        WorkManager.getInstance(this).enqueue(workRequest)
    }

    private fun createMovieNotificationChannel(){
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                getString(R.string.notification_stars_channel),
                getString(R.string.notification_stars_channel_title),
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

}