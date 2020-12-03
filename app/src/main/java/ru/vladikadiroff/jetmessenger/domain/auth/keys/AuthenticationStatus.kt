package ru.vladikadiroff.jetmessenger.domain.auth.keys

sealed class AuthenticationStatus {
    object Default: AuthenticationStatus()
    object isSuccessfull: AuthenticationStatus()
    class onCodeSent(val phoneNumber: String): AuthenticationStatus()
    class Exception(val message: String): AuthenticationStatus()
}