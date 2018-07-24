package com.nemesis.chargelogger

import com.nemesis.chargelogger.dagger.component.AppComponent
import com.nemesis.chargelogger.dagger.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class App : DaggerApplication() {
    companion object {
        lateinit var instance: App
    }

    @Inject
    lateinit var appComponent: AppComponent


    override fun onCreate() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
        instance = this
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent

}