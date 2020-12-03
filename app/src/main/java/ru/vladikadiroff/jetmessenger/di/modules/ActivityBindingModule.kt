package ru.vladikadiroff.jetmessenger.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.vladikadiroff.jetmessenger.di.modules.auth.AuthInteractorModule
import ru.vladikadiroff.jetmessenger.di.modules.auth.AuthModule
import ru.vladikadiroff.jetmessenger.di.modules.auth.AuthViewModelFactoryModule
import ru.vladikadiroff.jetmessenger.di.modules.auth.AuthFragmentBindingModule
import ru.vladikadiroff.jetmessenger.di.modules.messenger.MessengerFragmentBindingModule
import ru.vladikadiroff.jetmessenger.di.scopes.LoginScope
import ru.vladikadiroff.jetmessenger.di.scopes.MessengerScope
import ru.vladikadiroff.jetmessenger.ui.activities.MessengerActivity
import ru.vladikadiroff.jetmessenger.ui.activities.LoginActivity

@Module
abstract class ActivityBindingModule {

    @LoginScope
    @ContributesAndroidInjector
    (modules = [AuthModule::class,
        AuthInteractorModule::class,
        AuthViewModelFactoryModule::class,
        AuthFragmentBindingModule::class])
    abstract fun bindRegisterActivity(): LoginActivity

    @MessengerScope
    @ContributesAndroidInjector(modules = [MessengerFragmentBindingModule::class])
    abstract fun bindJetMessengerActivity(): MessengerActivity

}