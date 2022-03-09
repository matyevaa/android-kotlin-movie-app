package com.example.movietime.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repo: DetailedMovie)

    @Delete
    suspend fun delete(repo: DetailedMovie)

    @Query("SELECT * FROM DetailedMovie WHERE id = :id LIMIT 1")
    fun getCity(id: Int): Flow<DetailedMovie?>

    @Query("SELECT * FROM DetailedMovie")
    fun getAllInfo():Flow<List<DetailedMovie>>
}