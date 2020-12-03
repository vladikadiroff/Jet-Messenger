package ru.vladikadiroff.jetmessenger.ui.fragments.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import ru.vladikadiroff.jetmessenger.R

class SettingsFragment: PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }

}