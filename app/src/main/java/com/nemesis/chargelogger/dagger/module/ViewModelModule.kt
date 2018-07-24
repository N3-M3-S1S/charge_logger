package com.nemesis.chargelogger.dagger.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nemesis.chargelogger.dagger.key.ViewModelKey
import com.nemesis.chargelogger.ui.MainViewModel
import com.nemesis.chargelogger.ui.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindsMainViewModule(mainViewModel: MainViewModel): ViewModel

    @Binds
    @Singleton
    fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}