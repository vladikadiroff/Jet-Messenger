package ru.vladikadiroff.jetmessenger.di.modules.auth

import dagger.Module
import dagger.Provides
import ru.vladikadiroff.jetmessenger.di.scopes.LoginScope
import ru.vladikadiroff.jetmessenger.domain.AuthInteractorImpl
import ru.vladikadiroff.jetmessenger.repository.FirebaseAuthProvider

@Module
class AuthInteractorModule {

    @Provides
    @LoginScope
    fun provideAuthenticationInteractor(auth: FirebaseAuthProvider) = AuthInteractorImpl(auth)

}