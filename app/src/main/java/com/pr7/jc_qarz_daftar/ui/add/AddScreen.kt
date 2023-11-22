@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.pr7.jc_qarz_daftar.ui.add

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pr7.jc_qarz_daftar.R
import com.pr7.jc_qarz_daftar.data.room.Debt
import com.pr7.jc_qarz_daftar.data.room.RoomInstance
import com.pr7.jc_qarz_daftar.utils.DarkerGreen
import com.pr7.jc_qarz_daftar.utils.showlogd
import java.text.SimpleDateFormat
import java.util.Date


@ExperimentalMaterial3Api
@Composable
fun addScreen() {
    val context= LocalContext.current as Activity

    var username by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var igaveoraccept by remember { mutableStateOf(false) }


    val arraymoneytype = arrayOf("UZS", R.drawable.ruble, R.drawable.baseline_attach_money_24)
    var counttype by remember {
        mutableStateOf(0)
    }

    var termmm = stringResource(id = R.string.term)
    var date by remember {
        mutableStateOf(termmm)
    }

    var showDatePicker by remember {
        mutableStateOf(false)
    }
    var showDatePicker0 by remember {
        mutableStateOf(false)
    }

    var date0 by remember {
        mutableStateOf(convertMillisToDate(System.currentTimeMillis()))
    }
    var tdy = stringResource(id = R.string.today)
    var today by remember {
        mutableStateOf(tdy)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(12.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.add),
            fontFamily = FontFamily(Font(R.font.inter_bold)),
            fontSize = 30.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.tertiary

        )
        Spacer(modifier = Modifier.height(12.dp))
        //AddITem
        Column(modifier = Modifier.padding(1.dp), verticalArrangement = Arrangement.Center) {
            Card(
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground)
            ) {

                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Spacer(
                        modifier = Modifier
                            .height(80.dp)
                            .width(3.dp)
                            .background(if (igaveoraccept) DarkerGreen else Color.Red)
                    )
                    Column(modifier = Modifier.padding(5.dp)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Column() {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = price,
                                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                                        fontSize = 23.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        color = MaterialTheme.colorScheme.tertiary

                                    )

                                    if (counttype == 0) {
                                        Spacer(modifier = Modifier.width(3.dp))
                                        Text(
                                            text = "UZS",
                                            fontFamily = FontFamily(Font(R.font.inter_bold)),
                                            fontSize = 23.sp,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            color = MaterialTheme.colorScheme.tertiary

                                        )
                                    }
                                    if (counttype == 1) {
                                        Spacer(modifier = Modifier.width(3.dp))
                                        Icon(
                                            painter = painterResource(id = R.drawable.rublebold),
                                            contentDescription = "search",
                                            modifier = Modifier
                                                .size(20.dp),
                                            tint = MaterialTheme.colorScheme.tertiary
                                        )
                                    }
                                    if (counttype == 2) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.dollarbold2),
                                            contentDescription = "search",
                                            modifier = Modifier
                                                .size(30.dp),
                                            tint = MaterialTheme.colorScheme.tertiary,

                                            )
                                    }
                                }

                                Row {
                                    Text(
                                        text = username,
                                        fontFamily = FontFamily(Font(R.font.inter_medium)),
                                        fontSize = 13.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        color = MaterialTheme.colorScheme.tertiary,
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Text(
                                        text = surname,
                                        fontFamily = FontFamily(Font(R.font.inter_medium)),
                                        fontSize = 13.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        color = MaterialTheme.colorScheme.tertiary,
                                    )
                                }


                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Card(
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                                onClick = {

                                },
                                shape = RoundedCornerShape(7.dp)

                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.threedots),
                                    contentDescription = "search",
                                    modifier = Modifier
                                        .size(35.dp)
                                        .padding(10.dp),
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            }


                        }

                        Divider(color = if (igaveoraccept) DarkerGreen else Color.Red)

                        //Divider(color = DarkerGreen)
                        Row(Modifier.fillMaxWidth()) {
                            Text(
                                text = date0,
                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                fontSize = 13.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.tertiary,
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = date,
                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                fontSize = 13.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Clip,
                                color = MaterialTheme.colorScheme.tertiary,


                                )
                        }
                    }


                }


            }
        }
        //AddITem
        Spacer(modifier = Modifier.height(5.dp))
        Column(modifier = Modifier.padding(1.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.7f),
                    value = price,
                    onValueChange = {
                        price = it
                    },
                    label = { Text(text = "${stringResource(id = R.string.quantity)}") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                        focusedLabelColor = MaterialTheme.colorScheme.tertiary,
                        cursorColor = MaterialTheme.colorScheme.tertiary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.tertiary,
                        unfocusedLabelColor = MaterialTheme.colorScheme.tertiary,
                        containerColor = MaterialTheme.colorScheme.onBackground
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                    )
                Spacer(modifier = Modifier.width(5.dp))
                if (counttype == 0) {
                    Text(
                        text = "UZS",
                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                        fontSize = 25.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.tertiary

                    )
                }
                if (counttype == 1) {
                    Icon(
                        painter = painterResource(id = R.drawable.rublebold),
                        contentDescription = "search",
                        modifier = Modifier
                            .size(30.dp),
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }
                if (counttype == 2) {
                    Icon(
                        painter = painterResource(id = R.drawable.dollarbold),
                        contentDescription = "search",
                        modifier = Modifier
                            .size(35.dp),
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }

                Spacer(modifier = Modifier.width(5.dp))
                Card(
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground),
                    onClick = {
                        counttype++
                        if (counttype == 3) {
                            counttype = 0
                        }
                    },
                    shape = RoundedCornerShape(7.dp)

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.refresh),
                        contentDescription = "search",
                        modifier = Modifier
                            .size(35.dp)
                            .padding(5.dp),
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }
            }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                onValueChange = {
                    username = it
                },
                label = { Text(text = stringResource(id = R.string.username)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                    focusedLabelColor = MaterialTheme.colorScheme.tertiary,
                    cursorColor = MaterialTheme.colorScheme.tertiary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.tertiary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.tertiary,
                    containerColor = MaterialTheme.colorScheme.onBackground
                )
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = surname,
                onValueChange = {
                    surname = it
                },
                label = { Text(text = stringResource(id = R.string.surname)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                    focusedLabelColor = MaterialTheme.colorScheme.tertiary,
                    cursorColor = MaterialTheme.colorScheme.tertiary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.tertiary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.tertiary,
                    containerColor = MaterialTheme.colorScheme.onBackground
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Card(
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier.weight(1f)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { igaveoraccept = true },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(
                            modifier = Modifier
                                .height(40.dp)
                                .width(3.dp)
                                .background(DarkerGreen)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = stringResource(id = R.string.igave),
                            fontFamily = FontFamily(Font(R.font.inter_bold)),
                            fontSize = 13.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.clickable {
                                igaveoraccept = true
                            }

                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Card(
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                            onClick = {
                                igaveoraccept = true
                            },
                            shape = RoundedCornerShape(7.dp)

                        ) {
                            if (igaveoraccept) {
                                Icon(
                                    painter = painterResource(id = R.drawable.checkmark),
                                    contentDescription = "search",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .padding(4.dp),
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            }

                        }
                    }
                }
                Spacer(modifier = Modifier.width(5.dp))
                Card(
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier.weight(1f)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { igaveoraccept = false },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(
                            modifier = Modifier
                                .height(40.dp)
                                .width(3.dp)
                                .background(Color.Red)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = stringResource(id = R.string.Iaccepted),
                            fontFamily = FontFamily(Font(R.font.inter_bold)),
                            fontSize = 13.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.clickable {
                                igaveoraccept = false
                            }

                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Card(
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                            onClick = {
                                igaveoraccept = false
                            },
                            shape = RoundedCornerShape(7.dp)

                        ) {
                            if (!igaveoraccept) {
                                Icon(
                                    painter = painterResource(id = R.drawable.checkmark),
                                    contentDescription = "search",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .padding(4.dp),
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            }
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Card(
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier.weight(1f)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { showDatePicker0 = true },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(
                            modifier = Modifier
                                .height(40.dp)
                                .width(3.dp)
                                .background(DarkerGreen)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = today,
                            fontFamily = FontFamily(Font(R.font.inter_bold)),
                            fontSize = 13.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.clickable {
                                showDatePicker0 = true
                            }

                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Card(
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                            onClick = {
                                showDatePicker0 = true
                            },
                            shape = RoundedCornerShape(7.dp)

                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.calendar),
                                contentDescription = "search",
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(4.dp),
                                tint = MaterialTheme.colorScheme.tertiary
                            )


                        }
                    }
                }
                Spacer(modifier = Modifier.width(5.dp))
                Card(
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier.weight(1f)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { showDatePicker = true },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(
                            modifier = Modifier
                                .height(40.dp)
                                .width(3.dp)
                                .background(Color.Red)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = date,
                            fontFamily = FontFamily(Font(R.font.inter_bold)),
                            fontSize = 13.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.clickable {
                                showDatePicker = true
                            },


                            )
                        Spacer(modifier = Modifier.weight(1f))
                        Card(
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                            onClick = {
                                showDatePicker = true
                            },
                            shape = RoundedCornerShape(7.dp)

                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.calendar),
                                contentDescription = "search",
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(4.dp),
                                tint = MaterialTheme.colorScheme.tertiary
                            )

                        }
                    }
                }

            }
            Spacer(modifier = Modifier.weight(1f))
            Card(
                colors = CardDefaults.cardColors( MaterialTheme.colorScheme.onBackground),
                onClick = {

                showlogd(text = username)
                showlogd(text = surname)
                showlogd(text = price)
                showlogd(text = counttype.toString())
                showlogd(text = igaveoraccept.toString())
                showlogd(text = date0)
                showlogd(text = date)
                    if (username.isNotEmpty() && surname.isNotEmpty() && price.isNotEmpty()){
                        RoomInstance.debtDao.insertDebt(
                            debt = Debt(
                                uid = 0,
                                name = username,
                                surname = surname,
                                price = price,
                                moneytype = counttype,
                                startTime = date0,
                                endTime = date,
                                status = false,
                                acceptorgave = igaveoraccept
                            )
                        )
                        context.finish()
                    }



                },
                shape = ShapeDefaults.Medium,
                modifier = Modifier.fillMaxWidth(),

            ) {
                Column(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.add),
                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                        fontSize = 15.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.tertiary,

                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

    }

    if (showDatePicker) {
        MyDatePickerDialog(

            onDateSelected = { date = it },
            onDismiss = { showDatePicker = false },

            )
    }

    if (showDatePicker0) {
        MyDatePickerDialog(

            onDateSelected = {
                date0 = it
                today = it
            },
            onDismiss = { showDatePicker0 = false },

            )
    }
}


@ExperimentalMaterial3Api
@Composable
fun addScreenUpdate(debt: Debt) {
    val context= LocalContext.current as Activity

    var username by remember { mutableStateOf(debt.name) }
    var surname by remember { mutableStateOf(debt.surname) }
    var price by remember { mutableStateOf(debt.price) }
    var igaveoraccept by remember { mutableStateOf(debt.acceptorgave) }


    val arraymoneytype = arrayOf("UZS", R.drawable.ruble, R.drawable.baseline_attach_money_24)
    var counttype by remember {
        mutableStateOf(debt.moneytype)
    }

    var termmm = stringResource(id = R.string.term)
    var date by remember {
        mutableStateOf(debt.endTime)
    }

    var showDatePicker by remember {
        mutableStateOf(false)
    }
    var showDatePicker0 by remember {
        mutableStateOf(false)
    }

    var date0 by remember {
        mutableStateOf(debt.startTime)
    }
    var tdy = stringResource(id = R.string.today)
    var today by remember {
        mutableStateOf(date0)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(12.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.edit),
            fontFamily = FontFamily(Font(R.font.inter_bold)),
            fontSize = 30.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.tertiary

        )
        Spacer(modifier = Modifier.height(12.dp))
        //AddITem
        Column(modifier = Modifier.padding(1.dp), verticalArrangement = Arrangement.Center) {
            Card(
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground)
            ) {

                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Spacer(
                        modifier = Modifier
                            .height(80.dp)
                            .width(3.dp)
                            .background(if (igaveoraccept) DarkerGreen else Color.Red)
                    )
                    Column(modifier = Modifier.padding(5.dp)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Column() {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = price,
                                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                                        fontSize = 23.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        color = MaterialTheme.colorScheme.tertiary

                                    )

                                    if (counttype == 0) {
                                        Spacer(modifier = Modifier.width(3.dp))
                                        Text(
                                            text = "UZS",
                                            fontFamily = FontFamily(Font(R.font.inter_bold)),
                                            fontSize = 23.sp,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            color = MaterialTheme.colorScheme.tertiary

                                        )
                                    }
                                    if (counttype == 1) {
                                        Spacer(modifier = Modifier.width(3.dp))
                                        Icon(
                                            painter = painterResource(id = R.drawable.rublebold),
                                            contentDescription = "search",
                                            modifier = Modifier
                                                .size(20.dp),
                                            tint = MaterialTheme.colorScheme.tertiary
                                        )
                                    }
                                    if (counttype == 2) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.dollarbold2),
                                            contentDescription = "search",
                                            modifier = Modifier
                                                .size(30.dp),
                                            tint = MaterialTheme.colorScheme.tertiary,

                                            )
                                    }
                                }

                                Row {
                                    Text(
                                        text = username,
                                        fontFamily = FontFamily(Font(R.font.inter_medium)),
                                        fontSize = 13.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        color = MaterialTheme.colorScheme.tertiary,
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Text(
                                        text = surname,
                                        fontFamily = FontFamily(Font(R.font.inter_medium)),
                                        fontSize = 13.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        color = MaterialTheme.colorScheme.tertiary,
                                    )
                                }


                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Card(
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                                onClick = {

                                },
                                shape = RoundedCornerShape(7.dp)

                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.threedots),
                                    contentDescription = "search",
                                    modifier = Modifier
                                        .size(35.dp)
                                        .padding(10.dp),
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            }


                        }

                        Divider(color = if (igaveoraccept) DarkerGreen else Color.Red)

                        //Divider(color = DarkerGreen)
                        Row(Modifier.fillMaxWidth()) {
                            Text(
                                text = date0,
                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                fontSize = 13.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.tertiary,
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = date,
                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                fontSize = 13.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Clip,
                                color = MaterialTheme.colorScheme.tertiary,


                                )
                        }
                    }


                }


            }
        }
        //AddITem
        Spacer(modifier = Modifier.height(5.dp))
        Column(modifier = Modifier.padding(1.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.7f),
                    value = price,
                    onValueChange = {
                        price = it
                    },
                    label = { Text(text = "${stringResource(id = R.string.quantity)}") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                        focusedLabelColor = MaterialTheme.colorScheme.tertiary,
                        cursorColor = MaterialTheme.colorScheme.tertiary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.tertiary,
                        unfocusedLabelColor = MaterialTheme.colorScheme.tertiary,
                        containerColor = MaterialTheme.colorScheme.onBackground
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                    )
                Spacer(modifier = Modifier.width(5.dp))
                if (counttype == 0) {
                    Text(
                        text = "UZS",
                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                        fontSize = 25.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.tertiary

                    )
                }
                if (counttype == 1) {
                    Icon(
                        painter = painterResource(id = R.drawable.rublebold),
                        contentDescription = "search",
                        modifier = Modifier
                            .size(30.dp),
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }
                if (counttype == 2) {
                    Icon(
                        painter = painterResource(id = R.drawable.dollarbold),
                        contentDescription = "search",
                        modifier = Modifier
                            .size(35.dp),
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }

                Spacer(modifier = Modifier.width(5.dp))
                Card(
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground),
                    onClick = {
                        counttype++
                        if (counttype == 3) {
                            counttype = 0
                        }
                    },
                    shape = RoundedCornerShape(7.dp)

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.refresh),
                        contentDescription = "search",
                        modifier = Modifier
                            .size(35.dp)
                            .padding(5.dp),
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }
            }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                onValueChange = {
                    username = it
                },
                label = { Text(text = stringResource(id = R.string.username)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                    focusedLabelColor = MaterialTheme.colorScheme.tertiary,
                    cursorColor = MaterialTheme.colorScheme.tertiary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.tertiary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.tertiary,
                    containerColor = MaterialTheme.colorScheme.onBackground
                )
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = surname,
                onValueChange = {
                    surname = it
                },
                label = { Text(text = stringResource(id = R.string.surname)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                    focusedLabelColor = MaterialTheme.colorScheme.tertiary,
                    cursorColor = MaterialTheme.colorScheme.tertiary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.tertiary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.tertiary,
                    containerColor = MaterialTheme.colorScheme.onBackground
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Card(
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier.weight(1f)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { igaveoraccept = true },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(
                            modifier = Modifier
                                .height(40.dp)
                                .width(3.dp)
                                .background(DarkerGreen)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = stringResource(id = R.string.igave),
                            fontFamily = FontFamily(Font(R.font.inter_bold)),
                            fontSize = 13.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.clickable {
                                igaveoraccept = true
                            }

                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Card(
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                            onClick = {
                                igaveoraccept = true
                            },
                            shape = RoundedCornerShape(7.dp)

                        ) {
                            if (igaveoraccept) {
                                Icon(
                                    painter = painterResource(id = R.drawable.checkmark),
                                    contentDescription = "search",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .padding(4.dp),
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            }

                        }
                    }
                }
                Spacer(modifier = Modifier.width(5.dp))
                Card(
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier.weight(1f)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { igaveoraccept = false },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(
                            modifier = Modifier
                                .height(40.dp)
                                .width(3.dp)
                                .background(Color.Red)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = stringResource(id = R.string.Iaccepted),
                            fontFamily = FontFamily(Font(R.font.inter_bold)),
                            fontSize = 13.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.clickable {
                                igaveoraccept = false
                            }

                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Card(
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                            onClick = {
                                igaveoraccept = false
                            },
                            shape = RoundedCornerShape(7.dp)

                        ) {
                            if (!igaveoraccept) {
                                Icon(
                                    painter = painterResource(id = R.drawable.checkmark),
                                    contentDescription = "search",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .padding(4.dp),
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            }
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Card(
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier.weight(1f)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { showDatePicker0 = true },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(
                            modifier = Modifier
                                .height(40.dp)
                                .width(3.dp)
                                .background(DarkerGreen)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = today,
                            fontFamily = FontFamily(Font(R.font.inter_bold)),
                            fontSize = 13.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.clickable {
                                showDatePicker0 = true
                            }

                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Card(
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                            onClick = {
                                showDatePicker0 = true
                            },
                            shape = RoundedCornerShape(7.dp)

                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.calendar),
                                contentDescription = "search",
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(4.dp),
                                tint = MaterialTheme.colorScheme.tertiary
                            )


                        }
                    }
                }
                Spacer(modifier = Modifier.width(5.dp))
                Card(
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier.weight(1f)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { showDatePicker = true },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(
                            modifier = Modifier
                                .height(40.dp)
                                .width(3.dp)
                                .background(Color.Red)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = date,
                            fontFamily = FontFamily(Font(R.font.inter_bold)),
                            fontSize = 13.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.clickable {
                                showDatePicker = true
                            },


                            )
                        Spacer(modifier = Modifier.weight(1f))
                        Card(
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                            onClick = {
                                showDatePicker = true
                            },
                            shape = RoundedCornerShape(7.dp)

                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.calendar),
                                contentDescription = "search",
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(4.dp),
                                tint = MaterialTheme.colorScheme.tertiary
                            )

                        }
                    }
                }

            }
            Spacer(modifier = Modifier.weight(1f))
            Card(
                colors = CardDefaults.cardColors( MaterialTheme.colorScheme.onBackground),
                onClick = {

                    showlogd(text = username)
                    showlogd(text = surname)
                    showlogd(text = price)
                    showlogd(text = counttype.toString())
                    showlogd(text = igaveoraccept.toString())
                    showlogd(text = date0)
                    showlogd(text = date)
                    if (username.isNotEmpty() && surname.isNotEmpty() && price.isNotEmpty()){
                        RoomInstance.debtDao.updateDebt(
                            debt = Debt(
                                uid = debt.uid,
                                name = username,
                                surname = surname,
                                price = price,
                                moneytype = counttype,
                                startTime = date0,
                                endTime = date,
                                status = debt.status,
                                acceptorgave = igaveoraccept
                            )
                        )
                        context.finish()
                    }



                },
                shape = ShapeDefaults.Medium,
                modifier = Modifier.fillMaxWidth(),

                ) {
                Column(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.edit),
                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                        fontSize = 15.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.tertiary,

                        )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

    }

    if (showDatePicker) {
        MyDatePickerDialog(

            onDateSelected = { date = it },
            onDismiss = { showDatePicker = false },

            )
    }

    if (showDatePicker0) {
        MyDatePickerDialog(

            onDateSelected = {
                date0 = it
                today = it
            },
            onDismiss = { showDatePicker0 = false },

            )
    }
}


private fun convertMillisToDate(millis: Long): String {
    //val formatter = SimpleDateFormat("MMMM dd yyyy")
    val formatter = SimpleDateFormat("dd-MMM/yyy")
    return formatter.format(Date(millis))
}

@ExperimentalMaterial3Api
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun addScreenPreview() {

    addScreen()


}