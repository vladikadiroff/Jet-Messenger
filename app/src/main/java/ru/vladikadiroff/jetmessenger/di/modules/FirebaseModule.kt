package ru.vladikadiroff.jetmessenger.di.modules

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import ru.vladikadiroff.jetmessenger.di.scopes.ApplicationScope
import ru.vladikadiroff.jetmessenger.repository.FirebaseDatabaseProvider

@Module
class FirebaseModule {

    @Provides
    @ApplicationScope
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @ApplicationScope
    fun provideFirebaseDataBase() = FirebaseDatabase.getInstance()

    @Provides
    @ApplicationScope
    fun provideFirebaseDatabaseProvider(database: FirebaseDatabase, firebaseAuth: FirebaseAuth) =
        FirebaseDatabaseProvider(database, firebaseAuth)

}