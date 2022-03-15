package com.example.movietime.ui.stats

import android.os.Bundle
import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.movietime.R
import com.example.movietime.data.date

class StatsFragment : Fragment(R.layout.fragment_stats) {
    private val statsViewModel: StatsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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


}