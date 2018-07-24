package com.nemesis.chargelogger.service.impl

import android.os.BatteryManager
import com.nemesis.chargelogger.service.BatteryChargeService
import javax.inject.Inject

class BatteryChargeServiceImpl @Inject constructor(private val batteryManager: BatteryManager) : BatteryChargeService {

    override fun getCurrentChargeLevel(): Int = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
}