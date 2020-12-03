package ru.vladikadiroff.jetmessenger.di.modules.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import ru.vladikadiroff.jetmessenger.di.scopes.LoginScope
import ru.vladikadiroff.jetmessenger.repository.FirebaseAuthProvider
import ru.vladikadiroff.jetmessenger.ui.activities.LoginActivity
import ru.vladikadiroff.jetmessenger.utils.helpers.LiveDataTimer

@Module
class AuthModule {

    @Provides
    @LoginScope
    fun provideAuthenticationProvider(firebaseAuth: FirebaseAuth,
                                      firebaseDatabase: FirebaseDatabase,
                                      activity: LoginActivity) =
        FirebaseAuthProvider(firebaseAuth, firebaseDatabase, activity)

    @Provides
    @LoginScope
    fun provideTimer() = LiveDataTimer()

}