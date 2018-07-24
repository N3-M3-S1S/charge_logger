package com.nemesis.chargelogger.dagger.module

import android.content.Context
import android.content.SharedPreferences
import android.os.BatteryManager
import androidx.room.Room
import com.nemesis.chargelogger.App
import com.nemesis.chargelogger.service.BatteryChargeService
import com.nemesis.chargelogger.service.ChargeLoggingService
import com.nemesis.chargelogger.service.impl.BatteryChargeServiceImpl
import com.nemesis.chargelogger.service.impl.ChargeLoggingServiceImpl
import com.nemesis.chargelogger.storage.TemporaryChargeStorage
import com.nemesis.chargelogger.storage.db.Database
import com.nemesis.chargelogger.storage.impl.TemporaryChargeStorageImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {


    @Singleton
    @Provides
    fun providesSharedPreferences(app: App) = app.getSharedPreferences("tmp", Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun providesBatteryManager(app: App) = app.getSystemService(Context.BATTERY_SERVICE) as BatteryManager

    @Singleton
    @Provides
    fun providesBatteryChargeService(batteryManager: BatteryManager): BatteryChargeService = BatteryChargeServiceImpl(batteryManager)


    @Provides
    @Singleton
    fun providesDatabase(app: App) = Room.databaseBuilder(app.applicationContext, Database::class.java, "db").build()

    @Singleton
    @Provides
    fun providesTemporaryChargeStorage(batteryChargeService: BatteryChargeService, sharedPreferences: SharedPreferences): TemporaryChargeStorage =
            TemporaryChargeStorageImpl(batteryChargeService, sharedPreferences)

    @Provides
    @Singleton
    fun providesChargeLoggingService(database: Database, batteryChargeService: BatteryChargeService, temporaryChargeStorage: TemporaryChargeStorage):
            ChargeLoggingService = ChargeLoggingServiceImpl(database.getDao(), batteryChargeService, temporaryChargeStorage)
}