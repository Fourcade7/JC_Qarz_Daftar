@file:OptIn(ExperimentalMaterial3Api::class)

package com.pr7.jc_qarz_daftar.ui.add

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.lifecycleScope
import com.pr7.jc_qarz_daftar.data.pref.DARK_THEME
import com.pr7.jc_qarz_daftar.data.pref.DataStoreManager
import com.pr7.jc_qarz_daftar.data.room.Debt
import com.pr7.jc_qarz_daftar.data.room.RoomInstance

import com.pr7.jc_qarz_daftar.utils.statusbarcolorchangee
import com.pr7.jc_qarz_daftar.ui.home.ui.theme.JC_Qarz_DaftarTheme
import com.pr7.jc_qarz_daftar.ui.settings.dataStoreManagerhome
import com.pr7.jc_qarz_daftar.utils.CONTEXT
import com.pr7.jc_qarz_daftar.utils.showlogd
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerColors
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
class AddActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    dataStoreManagerhome= DataStoreManager(this)
        //get Object
        val debt = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("debtobj", Debt::class.java)
        } else {
            intent.getParcelableExtra<Debt>("debtobj")
        }
        val userdata= if (debt is Debt){
            debt
        } else {
            null
        }
        showlogd(debt.toString())
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
                if (debt==null){
                    addScreen()
                }else{
                    addScreenUpdate(debt = debt)
                }
              
            }
        }
    }
}
