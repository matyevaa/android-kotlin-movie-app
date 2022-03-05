package com.example.movietime.ui.detail
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.movietime.R
import com.example.movietime.data.Movie
import com.example.movietime.databinding.FragmentCalendarBinding
import com.example.movietime.databinding.FragmentDetailedBinding
import com.example.movietime.ui.calendar.CalendarViewModel


class MovieDetailFragment : Fragment() {
    private var _binding: FragmentDetailedBinding? = null
    private val args:MovieDetailFragmentArgs by navArgs()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailedBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val textbox = root.findViewById<TextView>(R.id.textView)
        textbox.text ="Clicked on " + args.movie.title + "\n Also back button is broken"

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}