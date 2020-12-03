package ru.vladikadiroff.jetmessenger.utils.extensions

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment

fun Fragment.withPostDelay(unit: () -> Unit, delay: Long) {
    Handler(Looper.getMainLooper()).postDelayed(unit, delay)
}

fun Fragment.withPostDelay(unit: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed(unit, 2000)
}