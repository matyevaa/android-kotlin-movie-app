package com.example.movietime.data
import androidx.room.Entity
import androidx.room.PrimaryKey

object API_Const {
    const val img_base_url = "https://image.tmdb.org/t/p/original/"
}

@Entity
data class Movie(
    //val budget: Int?,
    //val genres: Int,

    //@PrimaryKey
    //val id: Int, //

    //val runtime: Int, // int or null
    val backdrop_path: String?,
    val poster_path: String?,
    @PrimaryKey
    val overview: String, // string or null
    //val original_title: String,
    val popularity: Double,
    val release_date: String?,
    //val status: String,
    val title: String
)
