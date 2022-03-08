package com.example.movietime.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class DetailedMovie(
    @PrimaryKey
    val id: Int,
    val title: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val release_date: String?,
    val poster_path: String?,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    val budget: Int? = -1,
    val status: String? = "",
    val runtime: Int? = -1,
) : Serializable