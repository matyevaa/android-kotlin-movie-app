package com.example.movietime.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
<<<<<<< Updated upstream
    @Insert
    suspend fun insert(repo: Movie)
    @Delete
    suspend fun delete(repo: Movie){
=======
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repo: DetailedMovie)
    @Delete
    suspend fun delete(repo: DetailedMovie)
>>>>>>> Stashed changes

    @Query("SELECT * FROM DETAILEDMOVIE")
    fun getAllInfo():Flow<List<DetailedMovie>>
}