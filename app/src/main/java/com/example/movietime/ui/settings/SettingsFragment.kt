package com.example.movietime.ui.settings

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.preference.PreferenceFragmentCompat
import com.example.movietime.R


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.setBackgroundColor(Color.parseColor("#FFFFFF"))
        super.onViewCreated(view, savedInstanceState)
    }
}