package com.nemesis.chargelogger.dagger.module

import com.nemesis.chargelogger.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AndroidBindingModule {

    @ContributesAndroidInjector
    fun mainActivity(): MainActivity

}