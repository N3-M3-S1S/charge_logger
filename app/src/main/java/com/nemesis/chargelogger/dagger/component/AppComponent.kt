package com.nemesis.chargelogger.dagger.component

import com.nemesis.chargelogger.App
import com.nemesis.chargelogger.dagger.module.AndroidBindingModule
import com.nemesis.chargelogger.dagger.module.AppModule
import com.nemesis.chargelogger.dagger.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AndroidSupportInjectionModule::class, AndroidBindingModule::class, ViewModelModule::class])
interface AppComponent : AndroidInjector<App> {
    override fun inject(application: App)

    fun getWorkerSubcomponentBuilder(): WorkerSubcomponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }


}