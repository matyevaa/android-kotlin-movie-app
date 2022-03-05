package com.example.movietime.data

import java.io.Serializable

object API_Const {
    const val img_base_url = "https://image.tmdb.org/t/p/original/"
}

data class Movie(
    val id: Int,
    val title: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val release_date: String?,
    val poster_path: String?,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
) : Serializable