package ru.vladikadiroff.jetmessenger.ui.activities

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_messenger.*
import ru.vladikadiroff.jetmessenger.R
import ru.vladikadiroff.jetmessenger.utils.extensions.setupWithNavController

class MessengerActivity: DaggerAppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null
    private val navGraphIds = listOf(R.navigation.calls, R.navigation.chats, R.navigation.settings)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messenger)
        setSupportActionBar(toolbar)
        if(savedInstanceState == null) initNavigation()
    }

    private fun initNavigation() {
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.navHostFragment,
            intent = intent
        )

        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })

        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

}