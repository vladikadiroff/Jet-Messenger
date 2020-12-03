package ru.vladikadiroff.jetmessenger.domain

import androidx.lifecycle.MutableLiveData
import ru.vladikadiroff.jetmessenger.domain.auth.keys.AuthenticationStatus
import ru.vladikadiroff.jetmessenger.repository.FirebaseAuthProvider
import ru.vladikadiroff.jetmessenger.utils.extensions.default
import ru.vladikadiroff.jetmessenger.utils.extensions.set

class AuthInteractorImpl(private val auth: FirebaseAuthProvider): AuthInteractor {

    private val authenticationStatus =
            MutableLiveData<AuthenticationStatus>().default(AuthenticationStatus.Default)

    init{
        if (auth.isAuthenticate()) authenticationStatus.set(AuthenticationStatus.isSuccessfull)
        auth.addOnAuthenticationListener(onAuthenticationListener())
    }

    private fun onAuthenticationListener() = object: FirebaseAuthProvider.OnAuthenticationListener {
        override fun onSuccessful() {
            authenticationStatus.set(AuthenticationStatus.isSuccessfull)
        }

        override fun onCodeSent() {
            authenticationStatus.set(AuthenticationStatus.onCodeSent(auth.getPhoneNumber()))
        }

        override fun onVerificationFailed(exception: String) {
            authenticationStatus.set(AuthenticationStatus.Exception(exception))
        }
    }

    override fun verifyPhoneNumber(phoneNumber: String) {
        auth.verifyPhoneNumber(phoneNumber)
    }

    override fun resendVerificationCode() {
        auth.resendVerificationCode()
    }

    override fun getAuthenticationStatus(): MutableLiveData<AuthenticationStatus> {
        return authenticationStatus
    }

    override fun verifyVerificationCode(code: String) {
        auth.verifyVerificationCode(code)
    }

    override fun getPhone() = auth.getPhoneNumber()
}