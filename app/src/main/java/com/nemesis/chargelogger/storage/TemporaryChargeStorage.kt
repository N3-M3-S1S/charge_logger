package com.nemesis.chargelogger.storage

import com.nemesis.chargelogger.model.Charge

interface TemporaryChargeStorage {
    fun createTemporaryCharge()
    fun getTemporaryCharge(): Charge
    fun deleteTemporaryCharge()
}