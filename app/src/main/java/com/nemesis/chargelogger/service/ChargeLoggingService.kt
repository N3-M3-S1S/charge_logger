package com.nemesis.chargelogger.service

import androidx.lifecycle.LiveData
import com.nemesis.chargelogger.model.Charge

interface ChargeLoggingService {

    fun logChargerConnected()

    fun logChargerDisconnected()

    fun delete(charge: Charge)

    fun deleteAll()

    fun getChargeHistory(): LiveData<List<Charge>>

}