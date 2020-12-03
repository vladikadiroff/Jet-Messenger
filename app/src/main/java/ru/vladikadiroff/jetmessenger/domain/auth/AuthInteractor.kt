package ru.vladikadiroff.jetmessenger.domain

import androidx.lifecycle.MutableLiveData
import ru.vladikadiroff.jetmessenger.domain.auth.keys.AuthenticationStatus

interface AuthInteractor {
    fun verifyPhoneNumber(phoneNumber: String)
    fun resendVerificationCode()
    fun getAuthenticationStatus(): MutableLiveData<AuthenticationStatus>
    fun verifyVerificationCode(code: String)
    fun getPhone(): String
}