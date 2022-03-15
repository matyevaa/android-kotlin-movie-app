package com.example.movietime.ui.settings

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.movietime.R
import com.example.movietime.ui.BookmarkedMovieViewModel


class SettingsFragment : PreferenceFragmentCompat() {
    private val dbModel: BookmarkedMovieViewModel by viewModels()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        var darkmode = sharedPrefs.getBoolean(getString(R.string.darkmode), true)

        if(darkmode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            view.setBackgroundColor(Color.parseColor("#21FFFFFF"))

        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            view.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }

        sharedPrefs.registerOnSharedPreferenceChangeListener{ pref, key ->
            val mode = pref.getBoolean(key, true)
            if(mode){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                view.setBackgroundColor(Color.parseColor("#21FFFFFF"))

            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                view.setBackgroundColor(Color.parseColor("#FFFFFF"))}
        }

        val button: Preference? = findPreference(getString(R.string.delete_database))
        button?.setOnPreferenceClickListener {
            Log.d("Settings", "Delete Database")
            dbModel.deleteAll()
            true
        }

        super.onViewCreated(view, savedInstanceState)
    }
}