package ru.vladikadiroff.jetmessenger.viewmodels

import ru.vladikadiroff.jetmessenger.ui.abstracts.BaseLoginViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(): BaseLoginViewModel() {

    fun verifyPhoneNumber(phoneNumber: String) {
        interactor.verifyPhoneNumber(phoneNumber)
    }

}