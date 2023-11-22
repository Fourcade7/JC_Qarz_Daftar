package com.pr7.jc_qarz_daftar.data.pref

import android.content.SharedPreferences
import androidx.activity.ComponentActivity
import com.pr7.jc_qarz_daftar.utils.CONTEXT


const val TABLE_NAMEE="debts_table"
const val DARK_THEME="dark_theme"
const val LIGHT_THEME="light_theme"



fun saveString(key: String, value: String?) {
    val editor = CONTEXT.getSharedPreferences("Pr", ComponentActivity.MODE_PRIVATE)
        .edit() as SharedPreferences.Editor
    editor.putString(key, value)
    editor.commit()
}

fun loadString(key: String): String? {
    val sharedPreferences = CONTEXT.getSharedPreferences("Pr", ComponentActivity.MODE_PRIVATE)
    return sharedPreferences.getString(key, null)
}

fun saveBoolean(key: String, value: Boolean) {
    val editor = CONTEXT.getSharedPreferences("Pr", ComponentActivity.MODE_PRIVATE)
        .edit() as SharedPreferences.Editor
    editor.putBoolean(key, value)
    editor.commit()
}

fun loadBoolean(key: String): Boolean {
    val sharedPreferences = CONTEXT.getSharedPreferences("Pr", ComponentActivity.MODE_PRIVATE)
    return sharedPreferences.getBoolean(key, false)
}

fun saveInt(key: String, value: Int) {
    val editor = CONTEXT.getSharedPreferences("Pr", ComponentActivity.MODE_PRIVATE)
        .edit() as SharedPreferences.Editor
    editor.putInt(key, value)
    editor.commit()
}

fun loadInt(key: String): Int {
    val sharedPreferences = CONTEXT.getSharedPreferences("Pr", ComponentActivity.MODE_PRIVATE)
    return sharedPreferences.getInt(key, 0)
}