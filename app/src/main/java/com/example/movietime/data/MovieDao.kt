package com.example.movietime.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repo: DetailedMovie)
    @Delete
    suspend fun delete(repo: DetailedMovie)


    @Query("SELECT * FROM DETAILEDMOVIE")
    fun getAllInfo():Flow<List<DetailedMovie>>
}