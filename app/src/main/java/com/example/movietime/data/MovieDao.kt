package com.example.movietime.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    //TODO will need a query for getting movies with STATUS coming soon for calendar view
    //   SELECT * FROM DetailedMovie WHERE release_date > date()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repo: DetailedMovie)

    @Delete
    suspend fun delete(repo: DetailedMovie)

    @Query("SELECT * FROM DetailedMovie WHERE id = :id LIMIT 1")
    fun getCity(id: Int): Flow<DetailedMovie?>

    @Query("SELECT * FROM DetailedMovie")
    fun getAllInfo():Flow<List<DetailedMovie>>

    @Query("SELECT * FROM DetailedMovie WHERE budget = 0")
    fun getRecentInfo():Flow<List<DetailedMovie>>

    // STATS:
    /*
    Movie with longest runtime
    Select * FROM DetailedMovie WHERE runtime = (SELECT MAX(runtime) FROM DetailedMovie) limit 1

    Movie with highest popularity
    Select * FROM DetailedMovie WHERE popularity = (SELECT MAX(popularity) FROM DetailedMovie) limit 1

    Movie with highest budget
    Select * FROM DetailedMovie WHERE budget = (SELECT MAX(budget) FROM DetailedMovie) limit 1

    Total runtime of movies watched
    SELECT SUM(runtime) FROM DetailedMovie

    Most recently released movie seen
    SELECT * FROM DetailedMovie Order BY release_date desc limit 1

    Averages
    SELECT AVG(runtime) FROM DetailedMovie
    SELECT AVG(popularity) FROM DetailedMovie
     */
}