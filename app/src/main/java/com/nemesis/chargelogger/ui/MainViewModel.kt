package com.nemesis.chargelogger.ui

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.nemesis.chargelogger.model.Charge
import com.nemesis.chargelogger.service.ChargeLoggingService
import javax.inject.Inject

class MainViewModel @Inject constructor(private val chargeLoggingService: ChargeLoggingService) : ViewModel(), LifecycleObserver {
    val chargeHistory = chargeLoggingService.getChargeHistory()

    fun onChargeSwiped(charge: Charge) = chargeLoggingService.delete(charge)

    fun deleteHistory() = chargeLoggingService.deleteAll()

}
