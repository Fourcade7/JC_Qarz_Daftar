package com.pr7.jc_qarz_daftar.data.room

import androidx.room.Room
import com.pr7.jc_qarz_daftar.data.pref.TABLE_NAMEE
import com.pr7.jc_qarz_daftar.utils.CONTEXT
import com.pr7.jc_qarz_daftar.utils.db

object RoomInstance {

    var debtDao: DebtDao=db.debtDao()

}