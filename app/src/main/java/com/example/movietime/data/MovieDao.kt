package com.example.movietime.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert


@Dao
interface MovieDao {
    @Insert
    suspend fun insert(repo: Movie)
    @Delete
    suspend fun delete(repo: Movie){

    }
}