package com.example.movietime.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.movietime.R

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dark_button = view.findViewById<Switch>(R.id.switch_dark_mode)

        dark_button.setOnCheckedChangeListener { _, isChecked ->
            if(dark_button.isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                dark_button.text = "Disable Dark Mode"
            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                dark_button.text = "Enable Dark Mode"
            }
        }

    }
}