package com.example.movietime.data

class BookmarkedMovie(private val dao: MovieDao)
{
    suspend fun insertMovie(movie: DetailedMovie) = dao.insert(movie)
    suspend fun deleteMovie(movie: DetailedMovie) = dao.delete(movie)
    suspend fun deleteAll() = dao.deleteAll()

    fun getAllDetailedMovie()=dao.getAllInfo()
    fun getMovieByName(name: String)=dao.getRepoByName(name)

    fun getRecentMovie()=dao.getRecentInfo()
    suspend fun getRecentMovieOnce()=dao.getRecentInfoOnce()

    fun getLongestRuntimeMovie()=dao.getLongestRuntime()
    fun getHighestBudgetMovie()=dao.getHighestBudget()
    fun getTotalRunTime()=dao.getTotalRuntime()
    fun getMostRecentlyReleasedMovie()=dao.getMostRecentlyReleased()
    fun getAvgRunTime()=dao.getAverageRuntime()
}