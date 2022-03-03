package com.example.movietime.data
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    //val budget: Int?,
    //val genres: Int,

    //@PrimaryKey
    //val id: Int, //

    //val runtime: Int, // int or null
    //val backdrop_path: String, // string or null
    //val poster_path: String, // string or null*/
    @PrimaryKey
    val overview: String, // string or null
    //val original_title: String,
    val popularity: Double,
    val release_date: String?,
    //val status: String,
    val title: String
)
