package com.example.movietime.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.example.movietime.data.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.movietime.R

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>(){
    var movieList = listOf<Movie>()

    fun updateMovieList(newMovieList: List<Movie>?) {
        movieList = newMovieList ?: listOf()
        notifyDataSetChanged()
    }

    override fun getItemCount() = movieList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_title)
        private val overview: TextView = itemView.findViewById(R.id.tv_overview)
        private val release_date: TextView = itemView.findViewById(R.id.tv_release_date)
        private val popularity: TextView = itemView.findViewById(R.id.tv_popularity)


        fun bind(movie: Movie) {
            title.text = movie.title
            overview.text = movie.overview
            release_date.text = movie.release_date
            popularity.text = movie.popularity.toString()
        }
    }
}