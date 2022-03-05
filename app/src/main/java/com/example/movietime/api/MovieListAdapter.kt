package com.example.movietime.ui.home

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.movietime.data.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.movietime.R
import com.bumptech.glide.Glide
import com.example.movietime.data.API_Const

object MovieConst {
    const val list_item = 1
    const val tile_item = 2
}

class MovieListAdapter(private val onMovieClick: (Movie) -> Unit)
    : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>(){
    private val tag = "MovieListAdapter"
    private var vType = MovieConst.list_item
    var movieList = listOf<Movie>()

    fun updateMovieList(newMovieList: List<Movie>?) {
        movieList = newMovieList ?: listOf()
        notifyDataSetChanged()
    }

    fun setViewType(type: Int){vType = type}

    override fun getItemCount() = movieList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        var itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_item, parent, false)

        // set which layout file to use
        when (vType) {
            MovieConst.list_item -> itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_list_item, parent, false)
            MovieConst.tile_item -> itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_tile_item, parent, false)
        }
        return MovieViewHolder(itemView, vType, onMovieClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(API_Const.img_base_url+movieList[position].poster_path)
            .into(holder.itemView.findViewById(R.id.iv_poster))
        holder.bind(movieList[position])
    }

    class MovieViewHolder(itemView: View, vType: Int, val onMovieClick: (Movie) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_title)
        private val overview: TextView? = itemView.findViewById(R.id.tv_overview)
        private val release_date: TextView = itemView.findViewById(R.id.tv_release_date)
        private val popularity: TextView? = itemView.findViewById(R.id.tv_popularity)
        private val poster: ImageView = itemView.findViewById((R.id.iv_poster))
        private val vType:Int by lazy { vType }
        private var currentMovie: Movie? = null

        init {
            itemView.setOnClickListener {
                currentMovie?.let(onMovieClick)
            }
        }

        fun bind(movie: Movie) {
            currentMovie = movie
            when (vType) {
               MovieConst.list_item -> {
                    title.text = movie.title
                    release_date.text = movie.release_date
                    overview!!.text = movie.overview
                    popularity!!.text = movie.popularity.toString()
                }
                MovieConst.tile_item ->{
                    title.text = movie.title
                    release_date.text = movie.release_date
                }
            }
        }
    }
}