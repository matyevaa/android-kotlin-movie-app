package com.example.movietime.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Entity
data class DetailedMovie(
    @PrimaryKey
    val id: Int,
    val title: String,
    val original_title: String,
    val overview: String,
    //val popularity: Number, //Add Type Converter from Number to Int
    val vote_average: Double,
    val release_date: String?,
    val poster_path: String?,
    val backdrop_path: String?,
    @TypeConverters(GenreConverters::class)
    val genres: List<Genre>,
    val budget: Int?,
    val status: String?,
    val runtime: Int?,
) : Serializable

fun DetailedMovie.toMovie() = Movie(
    id = id,
    title = title,
    original_title = original_title,
    overview = overview,
    vote_average = vote_average,
    release_date = release_date,
    poster_path = poster_path,
    backdrop_path = backdrop_path,
    genre_ids = genres.toIDList(),
)

fun DetailedMovie.date(): Calendar {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val date: Date? = sdf.parse(this.release_date!!)
    val cal = Calendar.getInstance()
    cal.time = date!!
    return cal
}

fun List<DetailedMovie>.toMovieList(): MutableList<Movie> {
    val movies =  mutableListOf<Movie>()
    for (i in this.indices) {
        movies.add(this[i].toMovie())
    }
    return movies
}

