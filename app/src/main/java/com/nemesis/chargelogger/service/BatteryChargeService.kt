package com.nemesis.chargelogger.service

interface BatteryChargeService {
    fun getCurrentChargeLevel(): Int
}