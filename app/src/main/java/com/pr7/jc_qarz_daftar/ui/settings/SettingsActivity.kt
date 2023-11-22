package com.pr7.jc_qarz_daftar.ui.settings

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.lifecycleScope
import com.pr7.jc_qarz_daftar.R
import com.pr7.jc_qarz_daftar.data.pref.DARK_THEME
import com.pr7.jc_qarz_daftar.data.pref.DataStoreManager
import com.pr7.jc_qarz_daftar.data.pref.LIGHT_THEME
import com.pr7.jc_qarz_daftar.data.pref.loadBoolean
import com.pr7.jc_qarz_daftar.data.pref.saveBoolean

import com.pr7.jc_qarz_daftar.ui.home.ui.theme.JC_Qarz_DaftarTheme
import com.pr7.jc_qarz_daftar.utils.setLocale
import com.pr7.jc_qarz_daftar.utils.showlogd
import com.pr7.jc_qarz_daftar.utils.statusbarcolorchangee
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Locale

@SuppressLint("StaticFieldLeak")
lateinit var dataStoreManagerhome: DataStoreManager

class SettingsActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataStoreManagerhome= DataStoreManager(this@SettingsActivity)


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

                SettingsScreen()


            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SettingsScreen() {

    val kinds = listOf("Uzbek", "Русский", "English")
    val (selected, setSelected) = remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val themes = listOf(stringResource(id = R.string.darkmode), stringResource(id = R.string.lightmode))
    val dmode= stringResource(id = R.string.darkmode)

    val (selectedtheme, setSelectedtheme) = remember { mutableStateOf(dmode) }
    var expandedtheme by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
       Column(modifier = Modifier
           .fillMaxWidth()
           .padding(15.dp)) {
           Text(
               text = stringResource(id = R.string.settings),
               fontFamily = FontFamily(Font(R.font.inter_bold)),
               fontSize = 30.sp,
               maxLines = 1,
               overflow = TextOverflow.Ellipsis,
               color = MaterialTheme.colorScheme.tertiary

           )
            Spacer(modifier = Modifier.height(10.dp))
           Card(
               modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground),
               onClick = {
                   expanded=!expanded
               },
               shape = RoundedCornerShape(8.dp)
           ) {
               Row(modifier = Modifier
                   .fillMaxWidth()
                   .padding(10.dp),
                   verticalAlignment = Alignment.CenterVertically
                   ) {
                   Spacer(modifier = Modifier.width(5.dp))
                   Text(
                       text = stringResource(id = R.string.changelanguage),
                       fontFamily = FontFamily(Font(R.font.inter_bold)),
                       fontSize = 15.sp,
                       maxLines = 1,
                       overflow = TextOverflow.Ellipsis,
                       color = MaterialTheme.colorScheme.tertiary,
                       )
                   Spacer(modifier = Modifier.weight(1f))
                   Icon(
                       painter = painterResource(id = if (expanded) R.drawable.down_arrow else R.drawable.right_arrow),
                       contentDescription = "search",
                       modifier = Modifier
                           .size(15.dp)
                           .clickable {
                               expanded = !expanded
                           },
                       tint = MaterialTheme.colorScheme.tertiary
                   )
                   Spacer(modifier = Modifier.width(5.dp))
               }

              if (expanded){
                  Column {
                      KindRadioGroup(
                          mItems = kinds,
                          selected, setSelected
                      )
//                      Text(
//                          text = "Selected Option : $selected",
//                          textAlign = TextAlign.Center,
//                          modifier = Modifier.fillMaxWidth(),
//                      )
                  }
              }

           }

           Spacer(modifier = Modifier.height(10.dp))
           Card(
               modifier = Modifier.fillMaxWidth(),
               colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground),
               onClick = {
                   expandedtheme=!expandedtheme
               },
               shape = RoundedCornerShape(8.dp)
           ) {
               Row(modifier = Modifier
                   .fillMaxWidth()
                   .padding(10.dp),
                   verticalAlignment = Alignment.CenterVertically
               ) {
                   Spacer(modifier = Modifier.width(5.dp))
                   Text(
                       text = stringResource(id = R.string.themes),
                       fontFamily = FontFamily(Font(R.font.inter_bold)),
                       fontSize = 15.sp,
                       maxLines = 1,
                       overflow = TextOverflow.Ellipsis,
                       color = MaterialTheme.colorScheme.tertiary,
                   )
                   Spacer(modifier = Modifier.weight(1f))
                   Icon(
                       painter = painterResource(id = if (expandedtheme) R.drawable.down_arrow else R.drawable.right_arrow),
                       contentDescription = "search",
                       modifier = Modifier
                           .size(15.dp)
                           .clickable {
                               expandedtheme = !expandedtheme
                           },
                       tint = MaterialTheme.colorScheme.tertiary
                   )
                   Spacer(modifier = Modifier.width(5.dp))
               }

               if (expandedtheme){
                   Column {
                       KindRadioGroup(
                           mItems = themes,
                           selectedtheme, setSelectedtheme
                       )
//                      Text(
//                          text = "Selected Option : $selected",
//                          textAlign = TextAlign.Center,
//                          modifier = Modifier.fillMaxWidth(),
//                      )

                       showlogd(selectedtheme)
                       if (selectedtheme=="Dark mode" || selectedtheme=="Темный режим" || selectedtheme=="Tungi mavu"){
                           saveBoolean(DARK_THEME,true)
                           GlobalScope.launch {
                               dataStoreManagerhome.saveBoolean(DARK_THEME,true)
                           }

                       }
                       if (selectedtheme=="Light mode" || selectedtheme=="Светлый режим" || selectedtheme=="Yorqin mavzu"){
                           saveBoolean(DARK_THEME,false)
                           GlobalScope.launch {
                               dataStoreManagerhome.saveBoolean(DARK_THEME,false)
                           }
                       }


                   }
               }

           }
       }
    }
}
@Composable
fun KindRadioGroup(
    mItems: List<String>,
    selected: String,
    setSelected: (selected: String) -> Unit,
) {
   //  CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
           verticalArrangement = Arrangement.Center
        ) {
            mItems.forEach { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                   modifier = Modifier.clickable {
                       setSelected(item)
                   }
                ) {
                    RadioButton(
                        selected = selected == item,
                        onClick = {
                            setSelected(item)
//                            GlobalScope.launch {
//                                dataStoreManagerhome.save("lang",item)
//                            }

                        },
                        enabled = true,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.tertiary
                        )
                    )
                    Text(
                        text = item,
                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                        fontSize = 15.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.clickable {
                            setSelected(item)
                        }
                    )
                }
            }
        }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JC_Qarz_DaftarTheme {
        SettingsScreen()
    }
}