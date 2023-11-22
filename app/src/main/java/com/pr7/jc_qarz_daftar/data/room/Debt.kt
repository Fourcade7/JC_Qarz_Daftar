package com.pr7.jc_qarz_daftar.data.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pr7.jc_qarz_daftar.data.pref.TABLE_NAMEE

import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAMEE)
data class Debt(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val uid:Int=0,
    @ColumnInfo(name = "name")
    val name:String,
    @ColumnInfo(name = "surname")
    val surname:String,
    @ColumnInfo(name = "price")
    val price:String,
    @ColumnInfo(name = "moneytype")
    val moneytype:Int,
    @ColumnInfo(name = "starttime")
    val startTime:String,
    @ColumnInfo(name = "endtime")
    val endTime:String,
    @ColumnInfo(name = "status")
    val status:Boolean=false,
    @ColumnInfo(name = "acceptorgave")
    val acceptorgave:Boolean,
): Parcelable
