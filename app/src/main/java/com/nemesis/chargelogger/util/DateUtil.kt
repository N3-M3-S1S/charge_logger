@file:JvmName("DateUtil")

package com.nemesis.chargelogger.util

import java.text.SimpleDateFormat
import java.util.*

fun formatDateToString(date: Long): String = SimpleDateFormat("dd/MM/yy HH:mm", Locale.US).format(Date(date))