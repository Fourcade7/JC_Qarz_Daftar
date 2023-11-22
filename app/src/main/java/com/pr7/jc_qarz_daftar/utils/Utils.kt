package com.pr7.jc_qarz_daftar.utils

import android.app.LocaleManager
import android.os.Build
import android.os.LocaleList
import android.util.Log
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.os.LocaleListCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.util.Locale


private const val TAG = "PR77777"

fun showlogd(text:String){
    Log.d(TAG, text)
}

@Composable
fun statusbarcolorchangee(darkIcons: Boolean) {

    statusbarcolorchange(color = MaterialTheme.colorScheme.background, darkicons = darkIcons)

}

@Composable
fun statusbarcolorchange(color: Color, darkicons: Boolean) {
    // WindowCompat.setDecorFitsSystemWindows(window, false)

    //val systemUiController = rememberSystemUiController()
    //systemUiController.isStatusBarVisible = false
    val systemUiController = rememberSystemUiController()

    SideEffect {
        //JC_YaTaxi_PRv1Theme {
        systemUiController.setStatusBarColor(
            color = color,
            darkIcons = darkicons,

            )
    }

}


fun ComponentActivity.setLocale(language: String?) {

//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//        CONTEXT.getSystemService(LocaleManager::class.java).applicationLocales =
//            LocaleList.forLanguageTags(language)
//    }
//        else {
//            val appLocale: LocaleListCompat =
//                LocaleListCompat.forLanguageTags(language)
//            AppCompatDelegate.setApplicationLocales(appLocale)
//
//        }


    val config =resources.configuration
    val locale = Locale(language)
    Locale.setDefault(locale)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        config.setLocale(locale)
    else
        config.locale = locale

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        createConfigurationContext(config)
    resources.updateConfiguration(config, resources.displayMetrics)


}
