package com.example.movietime.data

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

object API_Const {
    const val img_base_url = "https://image.tmdb.org/t/p/original/"
}

data class Movie(
    val id: Int,
    val title: String,
    val original_title: String,
    val overview: String,
    val vote_average: Double,
    val release_date: String?,
    val poster_path: String?,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
) : Serializable

fun Movie.date(): Calendar {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val date: Date? = sdf.parse(this.release_date!!)
    val cal = Calendar.getInstance()
    cal.time = date!!
    return cal
}