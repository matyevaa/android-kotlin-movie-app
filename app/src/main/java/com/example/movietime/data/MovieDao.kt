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

    @Query("DELETE FROM DetailedMovie")
    suspend fun deleteAll()

    @Query("SELECT * FROM DetailedMovie WHERE id = :id LIMIT 1")
    fun getCity(id: Int): Flow<DetailedMovie?>

    @Query("SELECT * FROM DetailedMovie")
    fun getAllInfo():Flow<List<DetailedMovie>>

    @Query("SELECT * FROM DetailedMovie WHERE release_date > DATETIME('now', '-20 day') and release_date < DATETIME('now', '20 day')")
    fun getRecentInfo():Flow<List<DetailedMovie>>

    @Query("SELECT * FROM DetailedMovie WHERE original_title = :name LIMIT 1")
    fun getRepoByName(name: String): Flow<DetailedMovie?>

    @Query("SELECT * FROM DetailedMovie WHERE release_date > DATETIME('now', '-20 day') and release_date < DATETIME('now', '20 day')") //It is the godfather
    fun getRecentInfoOnce():List<DetailedMovie>



    // STATS:

    //Movie with longest runtime
    @Query("Select * FROM DetailedMovie WHERE runtime = (SELECT MAX(runtime) FROM DetailedMovie) limit 1")
    fun getLongestRuntime():Flow<DetailedMovie?>

    //Movie with highest popularity
    //@Query("Select * FROM DetailedMovie WHERE popularity = (SELECT MAX(popularity) FROM DetailedMovie) limit 1")
    //fun getHighestPopularity():Flow<DetailedMovie>

    //Movie with highest budget
    @Query("Select * FROM DetailedMovie WHERE budget = (SELECT MAX(budget) FROM DetailedMovie) limit 1")
    fun getHighestBudget():Flow<DetailedMovie?>

    //Total runtime of movies watched
    @Query("SELECT SUM(runtime) FROM DetailedMovie")
    fun getTotalRuntime(): Flow<Int?>

    //Most recently released movie seen
    @Query("SELECT * FROM DetailedMovie Order BY release_date desc limit 1")
    fun getMostRecentlyReleased():Flow<DetailedMovie?>

    //Averages
    @Query("SELECT AVG(runtime) FROM DetailedMovie")
    fun getAverageRuntime():Flow<Int?>

    //@Query("SELECT AVG(popularity) FROM DetailedMovie")
    //fun getAveragePopularity():Number?

}