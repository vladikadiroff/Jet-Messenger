package ru.vladikadiroff.jetmessenger.ui.abstracts

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.transition.MaterialContainerTransform
import dagger.android.support.DaggerFragment
import ru.vladikadiroff.jetmessenger.R
import javax.inject.Inject

abstract class BaseFragment<VM: ViewModel> (@LayoutRes layout: Int) : DaggerFragment(layout){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: VM
    protected abstract val viewModelClass: Class<VM>

    protected abstract val navHost: Int

    protected open val isSharedEnterTransition = false
    protected open val isPostponedEnterTransition = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(viewModelClass)
        initTransitionAnimation()
        initFragment()
    }

    private fun initTransitionAnimation() {
        if(isSharedEnterTransition) onSharedEnterTransition()
        if(isPostponedEnterTransition) onPostponedEnterTransition()
    }

    private fun onSharedEnterTransition() {
        sharedElementEnterTransition =
            MaterialContainerTransform().apply {
            // The drawing view is the id of the view above which the container transform
            // will play in z-space.
            drawingViewId = navHost
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            // Set the color of the scrim to transparent as we also want to animate the
            // list fragment out of view
            scrimColor = Color.TRANSPARENT
            //setAllContainerColors(R.attr.colorSurface)
        }
    }

    private fun onPostponedEnterTransition() {
        postponeEnterTransition()
        view?.doOnPreDraw { startPostponedEnterTransition() }
    }

    protected open fun initFragment() {}

}