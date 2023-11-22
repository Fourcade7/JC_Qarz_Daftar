package com.pr7.jc_qarz_daftar.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Debt::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun debtDao():DebtDao
}