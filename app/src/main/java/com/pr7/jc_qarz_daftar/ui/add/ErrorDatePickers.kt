@file:OptIn(ExperimentalMaterial3Api::class)

package com.pr7.jc_qarz_daftar.ui.add

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
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
import com.vanpra.composematerialdialogs.MaterialDialog
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


@Composable
fun mediumdatepickerdialog() {

    val state = rememberDatePickerState()
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        Column(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 15.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End
        ) {
            DatePickerDialog(
                shape = RoundedCornerShape(2.dp),
                onDismissRequest = {
                    openDialog.value = false
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                        }
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                        }
                    ) {
                        Text("CANCEL")
                    }
                }
            ) {
                DatePicker(
                    state = state
                )
            }
        }

    }
}

@Composable
fun datePickerPhlipLackner() {

    val applicationContext= LocalContext.current

    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    var pickedTime by remember {
        mutableStateOf(LocalTime.NOON)
    }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MMMM dd yyyy")
                .format(pickedDate)
        }
    }
    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("hh:mm")
                .format(pickedTime)
        }
    }

    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            dateDialogState.show()
        }) {
            Text(text = "Pick date")
        }
        Text(text = formattedDate)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            timeDialogState.show()
        }) {
            Text(text = "Pick time")
        }
        Text(text = formattedTime)
    }
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "Ok") {
                Toast.makeText(
                    applicationContext,
                    "Clicked ok",
                    Toast.LENGTH_LONG
                ).show()
            }
            negativeButton(text = "Cancel")
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date",

            ) {
            pickedDate = it
        }
    }
    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton(text = "Ok") {
                Toast.makeText(
                    applicationContext,
                    "Clicked ok",
                    Toast.LENGTH_LONG
                ).show()
            }
            negativeButton(text = "Cancel")
        },
    ) {
        timepicker(

            initialTime = LocalTime.NOON,
            title = "Pick a time",
            timeRange = LocalTime.MIDNIGHT..LocalTime.NOON,

            ) {
            pickedTime = it
        }
    }

}

@Composable
fun DatePickerDialogSample() {
    // Decoupled snackbar host state from scaffold state for demo purposes.
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    SnackbarHost(hostState = snackState, Modifier)
    val openDialog = remember { mutableStateOf(true) }
    // TODO demo how to read the selected date from the state.
    if (openDialog.value) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled = remember {
            derivedStateOf { datePickerState.selectedDateMillis != null }
        }
        DatePickerDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                openDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        snackScope.launch {
                            snackState.showSnackbar(
                                "Selected date timestamp: ${datePickerState.selectedDateMillis}"
                            )
                        }
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@Composable
fun pralertdialog() {

    Column() {
        var openDialog by remember { mutableStateOf(true) }

        Button(onClick = {
            openDialog = true
        }) {
            Text("Click me")
        }

        if (openDialog) {
            AlertDialog(
                // modifier = Modifier.clip(RoundedCornerShape(8.dp)), // corner rounded//not working
                onDismissRequest = {

                },
                title = { Text(text = "Dialog Title") },
                text = {
                    Column {
                        MyUI()
                    }
                },
                confirmButton = {
                    Button(onClick = { openDialog = false}) {
                        Text(text = "Click Me")
                    }
                },
                dismissButton = {

                }
            )
        }
    }



}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyUI() {
    val calendar = Calendar.getInstance()
    calendar.set(1990, 0, 22) // add year, month (Jan), date

    // set the initial date
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)
    Column(modifier = Modifier.padding(1.dp)) {
        DatePicker(
            state = datePickerState,
        )

    }


    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.ROOT)
    Text(
        text = "Selected date: ${formatter.format(Date(datePickerState.selectedDateMillis!!))}"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerView() {
    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis <= System.currentTimeMillis()
        }
    })
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        DatePicker(
            state = datePickerState
        )
        Spacer(
            modifier = Modifier.height(
                32.dp
            )
        )
        Text(
            text = selectedDate.toString(),
            color = Color.Red
        )
    }
}



private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MMMM dd yyyy")
    return formatter.format(Date(millis))
}
