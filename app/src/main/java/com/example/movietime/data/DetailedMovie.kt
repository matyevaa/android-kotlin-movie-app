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
   // val genre_ids: List<Int>,
    val budget: Int?,
    val status: String?,
    val runtime: Int?,
) : Serializable

fun DetailedMovie.toMovie() = Movie( //TODO maybe just overload movielist adapter instead?
    id = id,
    title= title,
    original_title = original_title,
    overview = overview,
    popularity = popularity,
    release_date = release_date,
    poster_path = poster_path,
    backdrop_path = backdrop_path,
    genre_ids = listOf(1,2,3),
)

fun List<DetailedMovie>.toMovieList(): MutableList<Movie> {
    val movies =  mutableListOf<Movie>()
    for (i in this.indices) {
        movies.add(this[i].toMovie())
    }
    return movies
}