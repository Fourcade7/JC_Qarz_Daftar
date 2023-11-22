package com.pr7.jc_qarz_daftar.ui.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.pr7.jc_qarz_daftar.ui.home.ui.theme.JC_Qarz_DaftarTheme
import com.pr7.jc_qarz_daftar.utils.statusbarcolorchangee
import java.text.SimpleDateFormat
import java.time.Year
import java.util.Calendar
import java.util.Date


@Composable
fun MyDatePickerDialog() {
    var date by remember {
        mutableStateOf("Open date picker dialog")
    }

    var showDatePicker by remember {
        mutableStateOf(true)
    }

    Box(contentAlignment = Alignment.Center) {
        Button(onClick = { showDatePicker = true }) {
            Text(text = date)
        }
    }

    if (showDatePicker) {
        MyDatePickerDialog(

            onDateSelected = { date = it },
            onDismiss = { showDatePicker = false },

            )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePickerDialog(
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val calendar = Calendar.getInstance()
    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)+1)
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)

    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    val darktheme=true
    JC_Qarz_DaftarTheme(darkTheme = darktheme) {

        statusbarcolorchangee(!darktheme)
        Column(modifier = Modifier.padding(horizontal = 25.dp)) {
            DatePickerDialog(
                shape = RoundedCornerShape(2.dp),
                onDismissRequest = { onDismiss() },
                confirmButton = {
                    Card(
                        colors = CardDefaults.cardColors( MaterialTheme.colorScheme.background),
                        onClick = {
                            onDateSelected(selectedDate)
                            onDismiss()
                        },
                        shape =ShapeDefaults.Medium
                        //  modifier = Modifier.width(80.dp).height(50.dp),

                    ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .width(80.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "OK",color = MaterialTheme.colorScheme.tertiary)
                        }
                    }

                },
                dismissButton = {
                    Card(
                        colors = CardDefaults.cardColors( MaterialTheme.colorScheme.background),
                        onClick = {

                            onDismiss()
                        },
                        shape =ShapeDefaults.Medium
                        //  modifier = Modifier.width(80.dp).height(50.dp),

                    ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .width(100.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Cancel", color = MaterialTheme.colorScheme.tertiary)
                        }
                    }
                },
                properties = DialogProperties(usePlatformDefaultWidth = false),
                tonalElevation = 5.dp,
                content = {


                    DatePicker(
                        state = datePickerState,
                        colors = DatePickerDefaults.colors(
                            titleContentColor = MaterialTheme.colorScheme.tertiary,
                            selectedDayContainerColor =  MaterialTheme.colorScheme.tertiary,
                            todayDateBorderColor =  MaterialTheme.colorScheme.tertiary,
                            todayContentColor =  MaterialTheme.colorScheme.tertiary,


                        )
                    )


                }
            )
        }
    }

}


private fun convertMillisToDate(millis: Long): String {
    //val formatter = SimpleDateFormat("MMMM dd yyyy")
    val formatter = SimpleDateFormat("dd-MMM/yyy")
    return formatter.format(Date(millis))
}
