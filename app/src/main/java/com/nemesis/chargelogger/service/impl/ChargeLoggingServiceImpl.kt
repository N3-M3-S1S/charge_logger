package com.nemesis.chargelogger.service.impl

import androidx.lifecycle.LiveData
import com.nemesis.chargelogger.model.Charge
import com.nemesis.chargelogger.service.BatteryChargeService
import com.nemesis.chargelogger.service.ChargeLoggingService
import com.nemesis.chargelogger.storage.TemporaryChargeStorage
import com.nemesis.chargelogger.storage.db.Dao
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class ChargeLoggingServiceImpl @Inject constructor(private val dao: Dao, private val batteryChargeService: BatteryChargeService, private val temporaryChargeStorage: TemporaryChargeStorage) : ChargeLoggingService {

    override fun delete(charge: Charge) {
        doAsync { dao.delete(charge) }
    }

    override fun deleteAll() {
        doAsync { dao.deleteAll() }
    }

    override fun logChargerConnected() {
        doAsync { temporaryChargeStorage.createTemporaryCharge() }
    }


    override fun logChargerDisconnected() {
        doAsync {
            val temporaryCharge = temporaryChargeStorage.getTemporaryCharge()
            if (temporaryCharge.chargerConnectedChargeLevel != -1) {
                temporaryCharge.chargerDisconnectedDate = System.currentTimeMillis()
                temporaryCharge.chargerDisconnectedChargeLevel = batteryChargeService.getCurrentChargeLevel()
                dao.insert(temporaryCharge)
            }
            temporaryChargeStorage.deleteTemporaryCharge()
        }
    }

    override fun getChargeHistory(): LiveData<List<Charge>> = dao.getAll()
}