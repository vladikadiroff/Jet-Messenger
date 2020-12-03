package ru.vladikadiroff.jetmessenger.ui.abstracts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.vladikadiroff.jetmessenger.domain.AuthInteractorImpl
import ru.vladikadiroff.jetmessenger.domain.auth.keys.AuthenticationStatus

abstract class BaseLoginViewModel(): ViewModel(){

    lateinit var status: MutableLiveData<AuthenticationStatus>
    protected lateinit var interactor: AuthInteractorImpl

    open fun initViewModel(interactor: AuthInteractorImpl) {
        this.interactor = interactor
        status = interactor.getAuthenticationStatus()
    }
}