package com.pr7.jc_qarz_daftar.utils

import android.app.Application
import androidx.room.Room
import com.pr7.jc_qarz_daftar.data.pref.TABLE_NAMEE
import com.pr7.jc_qarz_daftar.data.room.AppDatabase


lateinit var  CONTEXT:MyApp
lateinit var  db:AppDatabase

class MyApp constructor():Application() {



    override fun onCreate() {
        super.onCreate()
        CONTEXT=this

        db= Room.databaseBuilder(
            CONTEXT,
            AppDatabase::class.java, "MYDEBTS"
        ).allowMainThreadQueries().build()


    }
}