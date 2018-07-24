package com.nemesis.chargelogger.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.toWorkData
import com.nemesis.chargelogger.worker.CHARGER_STATUS_KEY
import com.nemesis.chargelogger.worker.ChargerLoggingWorker


class OnChargeBroadcastReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {
        val workBuilder = OneTimeWorkRequestBuilder<ChargerLoggingWorker>()

        val isChargerConnected = intent.action == Intent.ACTION_POWER_CONNECTED

        workBuilder.setInputData(mapOf(CHARGER_STATUS_KEY to isChargerConnected).toWorkData())

        WorkManager.getInstance()!!.enqueue(workBuilder.build())

    }


}
