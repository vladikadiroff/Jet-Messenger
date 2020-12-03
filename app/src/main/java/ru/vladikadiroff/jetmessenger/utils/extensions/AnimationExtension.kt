package ru.vladikadiroff.jetmessenger.utils.extensions

import android.view.View
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialElevationScale
import ru.vladikadiroff.jetmessenger.R

fun View.startShakeAnimation(){
    this.startAnimationFromResource(R.anim.anim_shake)
}

fun View.startAnimationFromResource(@AnimRes anim: Int){
    val animation = AnimationUtils.loadAnimation(this.context, anim)
    this.startAnimation(animation)
}

fun Fragment.exitAndReenterTransitionDelay(delay: Long){
    this.exitTransition = MaterialElevationScale(false).apply {
        duration = delay
    }
    this.reenterTransition = MaterialElevationScale(false).apply {
        duration = delay
    }
}