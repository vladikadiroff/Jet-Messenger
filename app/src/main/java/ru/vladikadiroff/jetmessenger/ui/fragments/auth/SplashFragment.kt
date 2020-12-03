package ru.vladikadiroff.jetmessenger.ui.fragments.auth

import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import kotlinx.android.synthetic.main.fragment_splash.*
import ru.vladikadiroff.jetmessenger.R
import ru.vladikadiroff.jetmessenger.domain.auth.keys.AuthenticationStatus
import ru.vladikadiroff.jetmessenger.ui.abstracts.BaseLoginFragment
import ru.vladikadiroff.jetmessenger.utils.extensions.ACTION_TO_LOGIN_FRAGMENT
import ru.vladikadiroff.jetmessenger.utils.extensions.navigateToMessengerActivity
import ru.vladikadiroff.jetmessenger.utils.extensions.transitionNavigateTo
import ru.vladikadiroff.jetmessenger.utils.extensions.withPostDelay
import ru.vladikadiroff.jetmessenger.viewmodels.SplashViewModel

class SplashFragment: BaseLoginFragment<SplashViewModel>(R.layout.fragment_splash){

    override val viewModelClass = SplashViewModel::class.java

    override fun initLoginFragment() {
        withPostDelay { initViewModel() }
    }

    private fun initViewModel(){
        val extras = FragmentNavigatorExtras(imageLogo to "imageLogo")
        viewModel.status.observe(this, Observer { status ->
            when(status) {
                is AuthenticationStatus.isSuccessfull -> navigateToMessengerActivity()
                else -> transitionNavigateTo(ACTION_TO_LOGIN_FRAGMENT, extras)
            }
        })
    }

}