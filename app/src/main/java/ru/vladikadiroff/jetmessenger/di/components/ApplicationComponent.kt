package ru.vladikadiroff.jetmessenger.di.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.vladikadiroff.jetmessenger.JetMessengerApplication
import ru.vladikadiroff.jetmessenger.di.modules.FirebaseModule
import ru.vladikadiroff.jetmessenger.di.modules.ActivityBindingModule
import ru.vladikadiroff.jetmessenger.di.scopes.ApplicationScope

@Component(modules = [AndroidSupportInjectionModule::class, FirebaseModule::class, ActivityBindingModule::class])
@ApplicationScope
interface ApplicationComponent: AndroidInjector<JetMessengerApplication>{
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder
        fun build():ApplicationComponent
    }
}