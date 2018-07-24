package com.nemesis.chargelogger.storage.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nemesis.chargelogger.model.Charge

@Dao
interface Dao {

    @Query("SELECT * FROM Charge ORDER BY chargerConnectedDate DESC")
    fun getAll(): LiveData<List<Charge>>

    @Insert
    fun insert(charge: Charge)

    @Delete
    fun delete(charge: Charge)

    @Query("DELETE FROM Charge")
    fun deleteAll()


}