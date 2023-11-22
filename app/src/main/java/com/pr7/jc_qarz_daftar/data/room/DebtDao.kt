package com.pr7.jc_qarz_daftar.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pr7.jc_qarz_daftar.data.pref.TABLE_NAMEE


@Dao
interface DebtDao {
    @Query("SELECT * FROM $TABLE_NAMEE")
    fun getAllDebs(): LiveData<List<Debt>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDebt(debt: Debt)
    @Update
    fun updateDebt(debt: Debt)
    @Delete
    fun deleteDebt(debt: Debt)
    @Query("DELETE FROM $TABLE_NAMEE")
    fun deleteAllDebts()

}