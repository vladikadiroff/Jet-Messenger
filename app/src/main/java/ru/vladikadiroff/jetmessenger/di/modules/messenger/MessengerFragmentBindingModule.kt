package ru.vladikadiroff.jetmessenger.di.modules.messenger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.vladikadiroff.jetmessenger.di.scopes.MessengerFragmentScope
import ru.vladikadiroff.jetmessenger.ui.fragments.EmptyFragment

@Module
abstract class MessengerFragmentBindingModule {

    @MessengerFragmentScope
    @ContributesAndroidInjector
    internal abstract fun provideEmptyFragment(): EmptyFragment


}