package ru.vladikadiroff.jetmessenger.ui.abstracts

import androidx.annotation.LayoutRes
import ru.vladikadiroff.jetmessenger.R
import ru.vladikadiroff.jetmessenger.domain.AuthInteractorImpl
import javax.inject.Inject

abstract class BaseLoginFragment<VM: BaseLoginViewModel>(@LayoutRes layout: Int):
        BaseFragment<VM>(layout) {

    @Inject
    lateinit var interactor: AuthInteractorImpl

    override val navHost = R.id.navHostFragmentRegister

    override fun initFragment() {
        viewModel.initViewModel(interactor)
        initLoginFragment()
    }

    protected open fun initLoginFragment(){}

}