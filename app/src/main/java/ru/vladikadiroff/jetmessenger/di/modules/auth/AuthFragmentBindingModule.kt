package ru.vladikadiroff.jetmessenger.di.modules.auth

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.vladikadiroff.jetmessenger.di.scopes.LoginFragmentScope
import ru.vladikadiroff.jetmessenger.ui.fragments.auth.EnterCodeFragment
import ru.vladikadiroff.jetmessenger.ui.fragments.auth.LoginFragment
import ru.vladikadiroff.jetmessenger.ui.fragments.auth.SplashFragment

@Module
abstract class AuthFragmentBindingModule{

    @LoginFragmentScope
    @ContributesAndroidInjector
    internal abstract fun provideSplashFragment(): SplashFragment

    @LoginFragmentScope
    @ContributesAndroidInjector
    internal abstract fun provideLoginFragment(): LoginFragment

    @LoginFragmentScope
    @ContributesAndroidInjector
    internal abstract fun provideEnterCodeFragment(): EnterCodeFragment

}