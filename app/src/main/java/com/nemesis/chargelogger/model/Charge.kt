package com.nemesis.chargelogger.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Charge(@PrimaryKey(autoGenerate = true) val id: Int? = null, val chargerConnectedDate: Long,
                  var chargerDisconnectedDate: Long = 0, val chargerConnectedChargeLevel: Int, var chargerDisconnectedChargeLevel: Int = -1)