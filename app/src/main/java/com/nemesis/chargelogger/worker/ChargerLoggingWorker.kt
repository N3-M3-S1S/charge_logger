package com.nemesis.chargelogger.worker

import androidx.work.Worker
import com.nemesis.chargelogger.App
import com.nemesis.chargelogger.service.ChargeLoggingService
import javax.inject.Inject

const val CHARGER_STATUS_KEY = "key"

class ChargerLoggingWorker : Worker() {
    @Inject
    lateinit var chargeLoggingService: ChargeLoggingService

    override fun doWork(): Result {
        App.instance.appComponent.getWorkerSubcomponentBuilder().build().inject(this)
        val isChargerConnected = inputData.getBoolean(CHARGER_STATUS_KEY, false)

        if (isChargerConnected) chargeLoggingService.logChargerConnected() else chargeLoggingService.logChargerDisconnected()

        return Result.SUCCESS
    }


}