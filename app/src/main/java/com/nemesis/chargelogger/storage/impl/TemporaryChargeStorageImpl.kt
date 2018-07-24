package com.nemesis.chargelogger.storage.impl

import android.content.SharedPreferences
import androidx.core.content.edit
import com.nemesis.chargelogger.model.Charge
import com.nemesis.chargelogger.service.BatteryChargeService
import com.nemesis.chargelogger.storage.TemporaryChargeStorage
import javax.inject.Inject

class TemporaryChargeStorageImpl @Inject constructor(private val batteryChargeService: BatteryChargeService, private val sharedPreferences: SharedPreferences) : TemporaryChargeStorage {
    private val connectedDateKey = "date"
    private val connectedChargeLevelKey = "charge"

    override fun createTemporaryCharge() {
        val connectedDate = System.currentTimeMillis()
        val connectedChargeLevel = batteryChargeService.getCurrentChargeLevel()
        sharedPreferences.edit {
            putLong(connectedDateKey, connectedDate)
            putInt(connectedChargeLevelKey, connectedChargeLevel)
        }
    }

    override fun getTemporaryCharge(): Charge {
        val startedTime = sharedPreferences.getLong(connectedDateKey, -1)
        val startedCharge = sharedPreferences.getInt(connectedChargeLevelKey, -1)
        return Charge(chargerConnectedDate = startedTime, chargerConnectedChargeLevel = startedCharge)
    }

    override fun deleteTemporaryCharge() {
        sharedPreferences.edit {
            remove(connectedDateKey)
            remove(connectedChargeLevelKey)
        }
    }

}