package com.example.movietime.data
import androidx.room.Entity
import androidx.room.PrimaryKey

<<<<<<< Updated upstream
@Entity
data class Movie(
    //val budget: Int?,
    //val genres: Int,
=======
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
>>>>>>> Stashed changes

    //@PrimaryKey
    //val id: Int, //

<<<<<<< Updated upstream
    //val runtime: Int, // int or null
    //val backdrop_path: String, // string or null
    //val poster_path: String, // string or null*/
    @PrimaryKey
    val overview: String, // string or null
    //val original_title: String,
=======
data class Movie(
    @PrimaryKey
    val id: Int,
    val title: String,
    val original_title: String,
    val overview: String,
>>>>>>> Stashed changes
    val popularity: Double,
    val release_date: String?,
    //val status: String,
    val title: String
)
