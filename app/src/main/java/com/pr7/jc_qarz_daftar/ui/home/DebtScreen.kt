package com.pr7.jc_qarz_daftar.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pr7.jc_qarz_daftar.R
import com.pr7.jc_qarz_daftar.data.room.Debt
import com.pr7.jc_qarz_daftar.data.room.RoomInstance
import com.pr7.jc_qarz_daftar.ui.add.AddActivity
import com.pr7.jc_qarz_daftar.ui.home.ui.theme.EditButtonColor
import com.pr7.jc_qarz_daftar.ui.settings.SettingsActivity
import com.pr7.jc_qarz_daftar.utils.DarkerGreen
import com.pr7.jc_qarz_daftar.utils.showlogd
import kotlinx.coroutines.launch


@ExperimentalMaterial3Api
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun debtScreen() {
    val context= LocalContext.current as Activity
    val arrayfashivka= arrayOf(
        Debt(name = "Thanh", surname = "Maritza", price = "100 000 UZS", startTime = "11.22.2023", endTime = "10.23.2024", status = false, moneytype = 0, acceptorgave = false),
        Debt(name = "John", surname = "Kramer", price = "500 000 UZS", startTime = "11.05.2023", endTime = "10.23.2025", status = false,moneytype = 0, acceptorgave = false),
        )
    val array by RoomInstance.debtDao.getAllDebs().observeAsState()

    val array2 by RoomInstance.debtDao.getAllDebs().observeAsState()

    var countUZS by remember {
        mutableStateOf(0)
    }
    var countUZS2 by remember {
        mutableStateOf(0)
    }

    var countRUBLE by remember {
        mutableStateOf(0)
    }
    var countRUBLE2 by remember {
        mutableStateOf(0)
    }

    var countUSA by remember {
        mutableStateOf(0)
    }
    var countUSA2 by remember {
        mutableStateOf(0)
    }

    //UZS
    LaunchedEffect(key1 = array2 ){
        countUZS=0
        if (array2!=null){
            //showlogd(array2!!.size.toString())
            array2!!.forEach {
                if (it.moneytype==0 && !it.acceptorgave && !it.status){
                    countUZS += it.price.toInt()
                }
            }
        }
    }
    LaunchedEffect(key1 = array2 ){
        countUZS2=0
        if (array2!=null){
            //showlogd(array2!!.size.toString())
            array2!!.forEach {
                if (it.moneytype==0 && it.acceptorgave && !it.status){
                    countUZS2 += it.price.toInt()
                }
            }
        }
    }

    //RUBLE
    LaunchedEffect(key1 = array2 ){
        countRUBLE=0
        if (array2!=null){
            //showlogd(array2!!.size.toString())
            array2!!.forEach {
                if (it.moneytype==1 && !it.acceptorgave && !it.status){
                    countRUBLE += it.price.toInt()
                }
            }
        }
    }
    LaunchedEffect(key1 = array2 ){
        countRUBLE2=0
        if (array2!=null){
            //showlogd(array2!!.size.toString())
            array2!!.forEach {
                if (it.moneytype==1 && it.acceptorgave && !it.status){
                    countRUBLE2 += it.price.toInt()
                }
            }
        }
    }
    //USA
    LaunchedEffect(key1 = array2 ){
        countUSA=0
        if (array2!=null){
            //showlogd(array2!!.size.toString())
            array2!!.forEach {
                if (it.moneytype==2 && !it.acceptorgave && !it.status){
                    countUSA += it.price.toInt()
                }
            }
        }
    }
    LaunchedEffect(key1 = array2 ){
        countUSA2=0
        if (array2!=null){
           //showlogd(array2!!.size.toString())
            array2!!.forEach {
                if (it.moneytype==2 && it.acceptorgave && !it.status){
                    countUSA2 += it.price.toInt()
                }
            }
        }
    }

    var search by remember { mutableStateOf("") }
    var searchmode by remember { mutableStateOf(false) }

    val listState = rememberLazyListState()
// Remember a CoroutineScope to be able to launch
    val coroutineScope = rememberCoroutineScope()


    if (searchmode){
        Text(
            text = stringResource(id = R.string.search),
            fontFamily = FontFamily(Font(R.font.inter_bold)),
            fontSize = 30.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.tertiary

        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = search,
            onValueChange = {
                search = it
            },
            placeholder = { Text(
                text = "${stringResource(id = R.string.username)},${stringResource(id = R.string.surname)},${stringResource(id = R.string.quantity)},${stringResource(id = R.string.time)}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            ) },

            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                focusedLabelColor = MaterialTheme.colorScheme.tertiary,
                cursorColor = MaterialTheme.colorScheme.tertiary,
                unfocusedBorderColor = MaterialTheme.colorScheme.tertiary,
                unfocusedLabelColor = MaterialTheme.colorScheme.tertiary,
                containerColor = MaterialTheme.colorScheme.onBackground
            ),
            maxLines = 1,

            trailingIcon = {
                Card(
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                    onClick = {
                        searchmode=false
                        search=""
                    },
                    shape = RoundedCornerShape(7.dp)

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.cancel),
                        contentDescription = "search",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(8.dp)
                            .clickable {
                                searchmode = false
                                search = ""
                            },
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }

            },
            leadingIcon = {

                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "search",
                        modifier = Modifier
                            .size(23.dp)
                            .clickable {

                            },
                        tint = MaterialTheme.colorScheme.tertiary
                    )

            },


        )
        Spacer(modifier = Modifier.height(10.dp))
    }else{
        //Navbar Screen
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Card(
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                    onClick = {
                    searchmode=true
                    },
                    shape = CircleShape

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription ="search" ,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(10.dp)
                            .clickable {
                                searchmode = true
                            },
                        tint =  MaterialTheme.colorScheme.tertiary
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Card(
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                    onClick = {
                    context.startActivity(Intent(context,SettingsActivity::class.java))
                    },
                    shape = CircleShape

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription ="settings" ,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(10.dp)
                            .clickable {
                                context.startActivity(Intent(context, SettingsActivity::class.java))
                            },
                        tint =  MaterialTheme.colorScheme.tertiary

                    )
                }
            }
            Text(
                text = stringResource(id = R.string.app_name),
                fontFamily = FontFamily(Font(R.font.inter_bold)),
                fontSize = 30.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.tertiary

            )
            Spacer(modifier = Modifier.height(12.dp))
            if (countUZS!=0 || countUZS2!=0){
                Row (Modifier.fillMaxWidth()){
                    Column(Modifier.weight(1f)) {
                        Row {
                            Text(
                                text = "-",
                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                fontSize = 15.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = Color.Red

                            )
                            Text(
                                text = "$countUZS UZS",
                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                fontSize = 15.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.tertiary

                            )
                        }
                        Divider(color =  Color.Red,modifier = Modifier.height(2.dp))
                    }

                    Spacer(modifier = Modifier.weight(0.2f),)
                    Column(Modifier.weight(1f)) {
                        Row {
                            Spacer(modifier = Modifier.weight(1f),)
                            Text(
                                text = "+",
                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                fontSize = 15.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = DarkerGreen

                            )
                            Text(
                                text = "$countUZS2 UZS",
                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                fontSize = 15.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.tertiary

                            )
                        }

                        Divider(color =  DarkerGreen, modifier = Modifier.height(2.dp))
                    }

                }
            }
            if (countRUBLE!=0 || countRUBLE2!=0){
                Row (Modifier.fillMaxWidth()){
                    Column(Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "-",
                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                fontSize = 15.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = Color.Red

                            )
                            Text(
                                text = "$countRUBLE",
                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                fontSize = 15.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.tertiary

                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.rublebold),
                                contentDescription = "search",
                                modifier = Modifier
                                    .size(15.dp),
                                tint = MaterialTheme.colorScheme.tertiary
                            )
                        }
                        Divider(color =  Color.Red,modifier = Modifier.height(2.dp))
                    }

                    Spacer(modifier = Modifier.weight(0.2f),)
                    Column(Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(modifier = Modifier.weight(1f),)
                            Text(
                                text = "+",
                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                fontSize = 15.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = DarkerGreen

                            )
                            Text(
                                text = "$countRUBLE2",
                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                fontSize = 15.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.tertiary

                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.rublebold),
                                contentDescription = "search",
                                modifier = Modifier
                                    .size(15.dp),
                                tint = MaterialTheme.colorScheme.tertiary
                            )
                        }

                        Divider(color =  DarkerGreen, modifier = Modifier.height(2.dp))
                    }

                }
            }
            if (countUSA!=0 || countUSA2!=0){
                Row (Modifier.fillMaxWidth()){
                    Column(Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "-",
                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                fontSize = 15.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = Color.Red

                            )
                            Text(
                                text = "$countUSA",
                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                fontSize = 15.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.tertiary

                            )

                            Icon(
                                painter = painterResource(id = R.drawable.dollarbold2),
                                contentDescription = "search",
                                modifier = Modifier
                                    .size(15.dp),
                                tint = MaterialTheme.colorScheme.tertiary,

                                )
                        }
                        Divider(color =  Color.Red,modifier = Modifier.height(2.dp))
                    }

                    Spacer(modifier = Modifier.weight(0.2f),)
                    Column(Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(modifier = Modifier.weight(1f),)
                            Text(
                                text = "+",
                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                fontSize = 15.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = DarkerGreen

                            )
                            Text(
                                text = "$countUSA2",
                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                fontSize = 15.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.tertiary

                            )

                            Icon(
                                painter = painterResource(id = R.drawable.dollarbold2),
                                contentDescription = "search",
                                modifier = Modifier
                                    .size(15.dp),
                                tint = MaterialTheme.colorScheme.tertiary,

                                )
                        }

                        Divider(color =  DarkerGreen, modifier = Modifier.height(2.dp))
                    }

                }
            }

            Spacer(modifier = Modifier.height(12.dp))

        }
        //Navbar Screen
    }



    //Debt Screen
    Box(modifier = Modifier.fillMaxSize()){


            if (array!=null){
                showlogd("${array!!.size} true")


                if (array!!.size<1){
                    Text(
                        text = stringResource(id = R.string.clickaddbutton),
                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                        fontSize = 23.sp,

                        color = MaterialTheme.colorScheme.tertiary,
                       modifier = Modifier.align(alignment = Alignment.Center)


                    )
                }else{
                    LazyColumn(
                        contentPadding = PaddingValues(bottom = 100.dp),
                    )
                    {
                        itemsIndexed(items = array!!.asReversed().filter {
                            it.name.contains(search,ignoreCase = true) ||
                                    it.surname.contains(search,ignoreCase = true) ||
                                    it.price.contains(search,ignoreCase = true) ||
                                    it.startTime.contains(search,ignoreCase = true) ||
                                    it.endTime.contains(search,ignoreCase = true)
                        }){index, item ->
                            debtItem(item)
                        }
                    }
                    coroutineScope.launch {
                        // Animate scroll to the 10th item
                        // listState.animateScrollToItem(listState.firstVisibleItemScrollOffset)
                    }
                }
            }


         FloatingActionButton(
             modifier = Modifier
                 .align(alignment = Alignment.BottomEnd)
                 .padding(20.dp),
             onClick = {
                       context.startActivity(Intent(context,AddActivity::class.java))
             },
                containerColor = MaterialTheme.colorScheme.onBackground
         ) {
             Icon(Icons.Filled.Add,"", tint = MaterialTheme.colorScheme.tertiary)
         }

    }
    //Debt Screen

}


//@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun debtItem(debt: Debt) {

    var expanded by remember { mutableStateOf(false) }
    val context= LocalContext.current as Activity
    Column(modifier = Modifier.padding(vertical = 5.dp)) {
        Card(
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground)
        ) {

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
               Spacer(modifier = Modifier
                   .height(80.dp)
                   .width(3.dp)
                   .background(color = if (debt.acceptorgave) DarkerGreen else Color.Red))
                Column(modifier = Modifier.padding( 5.dp), verticalArrangement = Arrangement.Center) {
                    Row(modifier = Modifier
                        .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Column() {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = debt.price,
                                    fontFamily = FontFamily(Font(R.font.inter_bold)),
                                    fontSize = 23.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    textDecoration = if (debt.status) TextDecoration.LineThrough else TextDecoration.None

                                )

                                if (debt.moneytype == 0) {
                                    Spacer(modifier = Modifier.width(3.dp))
                                    Text(
                                        text = "UZS",
                                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                                        fontSize = 23.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        color = MaterialTheme.colorScheme.tertiary,
                                        textDecoration = if (debt.status) TextDecoration.LineThrough else TextDecoration.None
                                    )
                                }
                                if (debt.moneytype == 1) {
                                    Spacer(modifier = Modifier.width(3.dp))
                                    Icon(
                                        painter = painterResource(id = R.drawable.rublebold),
                                        contentDescription = "search",
                                        modifier = Modifier
                                            .size(20.dp),
                                        tint = MaterialTheme.colorScheme.tertiary
                                    )
                                }
                                if (debt.moneytype == 2) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.dollarbold2),
                                        contentDescription = "search",
                                        modifier = Modifier
                                            .size(25.dp),
                                        tint = MaterialTheme.colorScheme.tertiary,

                                        )
                                }
                            }
                            Text(
                                text = "${debt.name} ${debt.surname}",
                                fontFamily = FontFamily(Font(R.font.inter_medium)),
                                fontSize = 13.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.tertiary,
                                textDecoration = if (debt.status) TextDecoration.LineThrough else TextDecoration.None
//                                modifier = Modifier
//                                    .drawWithContent {
//                                        drawContent()
//                                        val strokeWidth = 1.dp.toPx()
//                                        val verticalCenter = size.height / 2 + 2 * strokeWidth
//                                        drawLine(
//
//                                            color = Color.Red,
//                                            strokeWidth = strokeWidth,
//                                            start = Offset(0f, verticalCenter),
//                                            end = Offset(size.width, verticalCenter)
//                                        )
//                                    },

                            )

                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Card(
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                            onClick = {
                            expanded=true
                            },
                            shape = RoundedCornerShape(7.dp)

                        ) {
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                offset = DpOffset(x = (1).dp, y = (-40).dp)
                            ) {
                                DropdownMenuItem(
                                    text = {
                                           Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {

                                               Icon(
                                                   painter = painterResource(id = R.drawable.handshake),
                                                   contentDescription ="search" ,
                                                   modifier = Modifier
                                                       .size(35.dp)
                                                       .padding(2.dp),
                                                   tint =  MaterialTheme.colorScheme.tertiary
                                               )
                                               Spacer(modifier = Modifier.width(10.dp))
                                               Text(
                                                   text = stringResource(id = R.string.agreed),
                                                   fontFamily = FontFamily(Font(R.font.inter_bold)),
                                                   fontSize = 13.sp,
                                                   maxLines = 1,
                                                   overflow = TextOverflow.Ellipsis,
                                                   color = MaterialTheme.colorScheme.tertiary,
                                               )
                                           }
                                    },
                                    onClick = {
                                        val debtupdate=Debt(
                                            uid = debt.uid,
                                            name = debt.name,
                                            surname = debt.surname,
                                            price = debt.price,
                                            moneytype = debt.moneytype,
                                            startTime = debt.startTime,
                                            endTime = debt.endTime,
                                            status = !debt.status,
                                            acceptorgave = debt.acceptorgave
                                        )
                                        RoomInstance.debtDao.updateDebt(debt = debtupdate)
                                        expanded=false
                                    }
                                )
                                Divider()
                                DropdownMenuItem(
                                    text = {
                                        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {

                                            Icon(
                                                painter = painterResource(id = R.drawable.writing),
                                                contentDescription ="search" ,
                                                modifier = Modifier
                                                    .size(35.dp)
                                                    .padding(5.dp),
                                                tint =  EditButtonColor
                                            )
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Text(
                                                text = stringResource(id = R.string.edit),
                                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                                fontSize = 13.sp,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                                color = MaterialTheme.colorScheme.tertiary,
                                            )
                                        }
                                    },
                                    onClick = {

                                        val intent=Intent(context,AddActivity::class.java)
                                        intent.putExtra("debtobj",debt)
                                        context.startActivity(intent)
                                        expanded=false
                                    }
                                )
                                Divider()
                                DropdownMenuItem(
                                    text = {
                                        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {

                                            Icon(
                                                painter = painterResource(id = R.drawable.trash),
                                                contentDescription ="search" ,
                                                modifier = Modifier
                                                    .size(35.dp)
                                                    .padding(5.dp),
                                                tint =  Color.Red
                                            )
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Text(
                                                text = stringResource(id = R.string.delete),
                                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                                fontSize = 13.sp,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                                color = MaterialTheme.colorScheme.tertiary,
                                            )
                                        }
                                    },
                                    onClick = {
                                        RoomInstance.debtDao.deleteDebt(debt)
                                        expanded=false
                                    }
                                )
                            }
                            Icon(
                                painter = painterResource(id = R.drawable.threedots),
                                contentDescription ="search" ,
                                modifier = Modifier
                                    .size(35.dp)
                                    .padding(10.dp),
                                tint =  MaterialTheme.colorScheme.tertiary
                            )


                        }



                    }

                    Divider(color = if (debt.acceptorgave)DarkerGreen else Color.Red )
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            text = debt.startTime,
                            fontFamily = FontFamily(Font(R.font.inter_bold)),
                            fontSize = 13.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.tertiary,
                            textDecoration = if (debt.status) TextDecoration.LineThrough else TextDecoration.None
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = debt.endTime,
                            fontFamily = FontFamily(Font(R.font.inter_bold)),
                            fontSize = 13.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.tertiary,
                            textDecoration = if (debt.status) TextDecoration.LineThrough else TextDecoration.None
                        )
                    }
                }


            }



        }
    }
}

@ExperimentalMaterial3Api
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun debtScreenPreview() {
    debtScreen()
}