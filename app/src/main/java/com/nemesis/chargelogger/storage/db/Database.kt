package com.nemesis.chargelogger.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nemesis.chargelogger.model.Charge

@Database(entities = [Charge::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun getDao(): Dao
}