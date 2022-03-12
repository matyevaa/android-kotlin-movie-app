package com.example.movietime

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.work.*
import com.example.movietime.work.Worker
import java.util.concurrent.TimeUnit

class MovieSearchApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        launchWorker()
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
        val workRequest = OneTimeWorkRequestBuilder<Worker>().build()
        WorkManager.getInstance(this).enqueue(workRequest)
    }

    private fun createMovieNotificationChannel(){
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                getString(R.string.notification_stars_channel),
                getString(R.string.notification_stars_channel_title),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

}