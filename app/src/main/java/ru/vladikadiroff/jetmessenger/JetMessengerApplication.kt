package ru.vladikadiroff.jetmessenger

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.vladikadiroff.jetmessenger.di.components.DaggerApplicationComponent

class JetMessengerApplication: DaggerApplication() {

    companion object{
        lateinit var instance: JetMessengerApplication private set
    }


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return daggerApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    val daggerApplicationComponent =
            DaggerApplicationComponent.builder().application(this).build()


}