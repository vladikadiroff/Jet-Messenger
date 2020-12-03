package ru.vladikadiroff.jetmessenger.ui.activities

import androidx.navigation.findNavController
import dagger.android.support.DaggerAppCompatActivity
import ru.vladikadiroff.jetmessenger.R

class LoginActivity: DaggerAppCompatActivity(R.layout.activity_register){

    override fun onSupportNavigateUp() = findNavController(R.id.navHostFragmentRegister).navigateUp()

    override fun onBackPressed() {
        if (findNavController(R.id.navHostFragmentRegister)
                .currentDestination?.id == R.id.fragmentEnterCode) onSupportNavigateUp()
        else super.onBackPressed()
    }

}