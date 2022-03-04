package com.example.movietime.data
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.io.Serializable

object API_Const {
    const val img_base_url = "https://image.tmdb.org/t/p/original/"
}

@Entity
data class Movie(
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

    // TODO will need to populate from different api call
    // https://developers.themoviedb.org/3/movies/get-movie-details
    // Do we want a new data data class and not deal with transient?
    // Above api can get all data in class while plan movie search
    // does not return below data
    @Transient val budget: Int? = -1,
    @Transient val status: String? = "",
    @Transient val runtime: Int? = -1,
) : Serializable { // required for database
    constructor(id: Int, title: String, original_title: String, overview: String,
                popularity: Double, release_date: String?, poster_path: String?,
                backdrop_path: String?, genre_ids: List<Int>) :
            this(id, title, original_title, overview, popularity, release_date,
                poster_path,backdrop_path,genre_ids, -1, "", -1)
}