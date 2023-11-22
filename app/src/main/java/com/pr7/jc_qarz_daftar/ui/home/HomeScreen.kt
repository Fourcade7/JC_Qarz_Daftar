package com.pr7.jc_qarz_daftar.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pr7.jc_qarz_daftar.R
import com.pr7.jc_qarz_daftar.ui.home.ui.theme.JC_Qarz_DaftarTheme
import com.pr7.jc_qarz_daftar.utils.statusbarcolorchange
import com.pr7.jc_qarz_daftar.utils.statusbarcolorchangee


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreen() {

        Column (modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)){
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
                ) {

                navbarScreen()
                debtScreen()
            }


        }








}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun homeScreenPreview() {

    homeScreen()
}