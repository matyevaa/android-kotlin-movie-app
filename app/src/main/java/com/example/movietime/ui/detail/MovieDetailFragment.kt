package com.example.movietime.ui.detail
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.movietime.R
import com.example.movietime.data.Movie

const val EXTRA_MOVIE = "Movie"

class MovieDetailFragment : AppCompatActivity() {
    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_activity_movie_detail)
        Log.d("click", "clicked2")
        if(intent!=null && intent.hasExtra(EXTRA_MOVIE)) {
            Log.d("clicked", "inside intent fragment")
            movie = intent.getSerializableExtra(EXTRA_MOVIE) as Movie // return the extra as a ser object
            // put weather to the UI
            findViewById<TextView>(R.id.tv_title).text
            findViewById<TextView>(R.id.tv_overview).text
            findViewById<TextView>(R.id.tv_release_date).text
            findViewById<TextView>(R.id.tv_popularity).text
            findViewById<TextView>(R.id.tv_rating).text
            findViewById<ImageView>(R.id.iv_poster)
        }
    }
}