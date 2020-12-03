package ru.vladikadiroff.jetmessenger.utils.extensions

import android.app.Activity
import android.content.Intent
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import ru.vladikadiroff.jetmessenger.R
import ru.vladikadiroff.jetmessenger.ui.activities.LoginActivity
import ru.vladikadiroff.jetmessenger.ui.activities.MessengerActivity

const val ACTION_TO_LOGIN_FRAGMENT = R.id.action_splashFragment_to_loginFragment2
const val ACTION_TO_ENTER_CODE_FRAGMENT = R.id.action_loginFragment_to_fragmentEnterCode

fun Fragment.navigateUp(){
    this.findNavController().navigateUp()
}

fun Fragment.navigateTo(@IdRes currentDestination: Int? = null, @IdRes action: Int) {
    if (currentDestination == null) this.findNavController().navigate(action)
    else if (findNavController().currentDestination?.id == R.id.loginFragment) {
        this.findNavController().navigate(action)
    }
}

fun Fragment.navigateToMessengerActivity(){
    (activity as LoginActivity).replaceActivity(MessengerActivity())
}

fun Fragment.navigateToLoginActivity(){
    (activity as MessengerActivity).replaceActivity(LoginActivity())
}

fun Fragment.transitionNavigateTo(@IdRes action: Int, extras: FragmentNavigator.Extras){
    this.exitAndReenterTransitionDelay(
        this.resources.getInteger(R.integer.reply_motion_duration_large).toLong())
    this.findNavController().navigate(action, null, null, extras)
}

fun Activity.replaceActivity(activity: Activity){
    val intent = Intent(this, activity::class.java)
    this.startActivity(intent)
    this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    this.finish()
}