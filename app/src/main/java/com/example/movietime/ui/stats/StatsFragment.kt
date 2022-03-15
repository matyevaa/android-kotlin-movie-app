package com.example.movietime.ui.stats

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.movietime.R
import com.example.movietime.data.DetailedMovie
import com.example.movietime.data.date

class StatsFragment : Fragment(R.layout.fragment_stats) {
    private val statsViewModel: StatsViewModel by viewModels()
    lateinit var longest : String
    lateinit var total : String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        statsViewModel.longestRunTimeMovie.observe(viewLifecycleOwner){detailedMovie ->
            view.findViewById<TextView>(R.id.Longest_runtim_movie).text =
                getString(R.string.longest_runtime_movie,detailedMovie?.title,detailedMovie?.runtime)
        }

        statsViewModel.highestBudgetMovie.observe(viewLifecycleOwner){detailedMovie ->
            view.findViewById<TextView>(R.id.Highest_Budget_Movie).text =
                getString(R.string.highest_budget_movie,detailedMovie?.title,detailedMovie?.budget)
        }

        statsViewModel.totalRunTime.observe(viewLifecycleOwner){TotRuntime ->
            view.findViewById<TextView>(R.id.Total_Runtime).text =
                getString(R.string.total_runtime,TotRuntime)
        }

        statsViewModel.mostRecentReleasedMovie.observe(viewLifecycleOwner){detailedMovie ->
            view.findViewById<TextView>(R.id.Most_Recently_Released).text =
                getString(R.string.most_recent_released_movie,detailedMovie?.title)
        }

        statsViewModel.avgRuntime.observe(viewLifecycleOwner){AvgRuntime ->
            view.findViewById<TextView>(R.id.Avg_Runtime).text =
                getString(R.string.avg_runtime,AvgRuntime)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_stats_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_shar_stats -> {
                shareStats()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareStats() {
        statsViewModel.mostRecentReleasedMovie.observe(viewLifecycleOwner){detailedMovie->
             longest = getString(R.string.most_recent_released_movie_name,detailedMovie?.title)
        }
        statsViewModel.totalRunTime.observe(viewLifecycleOwner){runtime->
            total = getString(R.string.total_runtime_time,runtime)
        }
        val text = getString(R.string.share_stats_text,longest,total)
        Log.d("Share",text)
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(intent, null))
    }

}