package com.pr7.jc_qarz_daftar.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pr7.jc_qarz_daftar.data.pref.DARK_THEME
import com.pr7.jc_qarz_daftar.data.pref.DataStoreManager
import com.pr7.jc_qarz_daftar.data.room.Debt
import com.pr7.jc_qarz_daftar.data.room.RoomInstance
import com.pr7.jc_qarz_daftar.ui.home.ui.theme.JC_Qarz_DaftarTheme
import com.pr7.jc_qarz_daftar.ui.settings.dataStoreManagerhome
import com.pr7.jc_qarz_daftar.utils.statusbarcolorchange
import com.pr7.jc_qarz_daftar.utils.statusbarcolorchangee
import kotlinx.coroutines.launch

class HomeActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataStoreManagerhome= DataStoreManager(this)

        setContent {
            var darktheme by remember {
                mutableStateOf(false)
            }
            lifecycleScope.launch {
                dataStoreManagerhome.loadBoolean(DARK_THEME).collect{
                    darktheme=it
                }
            }

            JC_Qarz_DaftarTheme(darkTheme = darktheme) {
                statusbarcolorchangee(!darktheme)

                homeScreen()


            }

//            RoomInstance.debtDao.insertDebt(
//                debt = Debt(
//                    uid = 0,
//                    name = "Pr",
//                    surname = "Kacey",
//                    price = "10000",
//                    moneytype = 1,
//                    startTime = "Fri 17 2023",
//                    endTime = "Fri 18 2023",
//                    status = false,
//                    acceptorgave = false
//                )
//            )

            }
    }





}
