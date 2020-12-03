package ru.vladikadiroff.jetmessenger.di.modules.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.vladikadiroff.jetmessenger.di.factories.ViewModelFactory
import ru.vladikadiroff.jetmessenger.di.keys.ViewModelKey
import ru.vladikadiroff.jetmessenger.di.scopes.LoginScope
import ru.vladikadiroff.jetmessenger.viewmodels.EnterCodeViewModel
import ru.vladikadiroff.jetmessenger.viewmodels.LoginViewModel
import ru.vladikadiroff.jetmessenger.viewmodels.SplashViewModel

@Module
abstract class AuthViewModelFactoryModule {

    @Binds
    @LoginScope
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory):
            ViewModelProvider.Factory
    
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun loginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun splashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EnterCodeViewModel::class)
    internal abstract fun enterCodeViewModel(enterCodeViewModel: EnterCodeViewModel): ViewModel

}