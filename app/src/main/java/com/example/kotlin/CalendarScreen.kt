package com.example.kotlin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.util.*
import java.time.format.TextStyle as TextStyle1

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        CalendarView()
        FloatingActionButton(
            onClick = { /*TODO: Handle click*/ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarView(modifier: Modifier = Modifier) {
    val currentMonth = remember { Calendar.getInstance().get(Calendar.MONTH) }
    val daysInMonth = remember { Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH) }
    val firstDayOfMonth = remember { Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, 1) }.get(Calendar.DAY_OF_WEEK) }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        // Agrega la imagen de fondo aquí
        Image(
            painter = painterResource(id = R.drawable.bg), // Reemplaza con la ruta de tu imagen
            contentDescription = null, // Puedes agregar una descripción si es necesario
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Ajusta la escala de la imagen según tus necesidades
        )
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { /*TODO: Implementar la lógica para ir a esa pestaña*/ }) {
                    Text("barra")
                }
                Text(
                    text = LocalDate.now().month.getDisplayName(TextStyle1.FULL, Locale.getDefault()),
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
                )
                Button(onClick = { /*TODO: Implementar la lógica para ir a esa pestaña*/ }) {
                    Text("notif")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(7) { day ->
                    Text(
                        text = getDayOfWeekAbbreviation(day),
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(7) { day ->
                    Text(
                        text = getDayOfmonthAbbreviation(day),
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            HourlyGrid()
        }

        BlockGrid(
            modifier = Modifier
                .align(Alignment.Center)
                .width(300.dp)
                .height(90.dp)
                .offset(y = (-133).dp),
            offsetX = 5.dp // Ahora es 50.dp en lugar de solo 50

        )
        BlockGridLym(
            modifier = Modifier
                .align(Alignment.Center)
                .width(200.dp)
                .height(90.dp)
                .offset(y = (-33).dp),
            offsetX = 0.dp,
            horizontalSpacing = 13.dp // Espacio horizontal entre bloques en BlockGridLym
        )
        BlockGridDSW(
            modifier = Modifier
                .align(Alignment.Center)
                .width(200.dp)
                .height(90.dp)
                .offset(y = (168).dp),
            offsetX = 6.dp,
            horizontalSpacing = 13.dp // Espacio horizontal entre bloques en BlockGridLym
        )
        BlockGridSoccer(
            modifier = Modifier
                .align(Alignment.Center)
                .width(200.dp)
                .height(90.dp)
                .offset(y = (168).dp),
            offsetX = 206.dp,
            horizontalSpacing = 13.dp // Espacio horizontal entre bloques en BlockGridLym
        )

    }
}

private fun getDayOfWeekAbbreviation(dayOfWeek: Int): String {
    return when (dayOfWeek) {
        1 -> "M"
        2 -> "T"
        3 -> "W"
        4 -> "T"
        5 -> "F"
        else -> ""
    }
}

private fun getDayOfmonthAbbreviation(dayOfWeek: Int): String {
    return when (dayOfWeek) {
        1 -> "26"
        2 -> "27"
        3 -> "28"
        4 -> "29"
        5 -> "30"
        else -> ""
    }
}

@Composable
fun HourlyGrid() {
    val hours = (8..18).toList()

    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        hours.forEach { hour ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = if (hour < 10) "0$hour:00" else "$hour:00", fontSize = 12.sp)
                Divider(color = Color.White, thickness = 1.dp)
            }
        }
    }
}

@Composable
fun BlockGrid(
    modifier: Modifier = Modifier,
    offsetX: Dp =0.dp // Cambiado a Dp
) {
    Row(
        modifier = modifier
            .offset(x = offsetX), // Aplicamos el desplazamiento horizontal
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Block(
            text = "MOS",
            modifier = Modifier
                .width(40.dp)
                .height(120.dp)
        )
        Block(
            text = "MOS",
            modifier = Modifier
                .width(40.dp)
                .height(120.dp)
        )
        Block(
            text = "MOS",
            modifier = Modifier
                .width(40.dp)
                .height(120.dp)
        )
    }
}

@Composable
fun Block(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF8F98FF))
            .border(
                width = 1.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 8.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
@Composable
fun BlockGridLym(
    modifier: Modifier = Modifier,
    offsetX: Dp = 0.dp,
    horizontalSpacing: Dp = 8.dp // Espacio horizontal entre bloques
) {
    Row(
        modifier = modifier
            .offset(x = offsetX)
            .padding(horizontal = horizontalSpacing), // Aplicar el espaciado horizontal
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BlockLym(
            text = "LyM",
            modifier = Modifier
                .width(40.dp)
                .height(120.dp)
        )
        BlockLym(
            text = "LyM",
            modifier = Modifier
                .width(40.dp)
                .height(120.dp)
        )
    }
}

@Composable
fun BlockLym(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFFF7648))
            .border(
                width = 1.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 8.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BlockGridDSW(
    modifier: Modifier = Modifier,
    offsetX: Dp = 0.dp,
    horizontalSpacing: Dp = 8.dp // Espacio horizontal entre bloques
) {
    Row(
        modifier = modifier
            .offset(x = offsetX)
            .padding(horizontal = horizontalSpacing), // Aplicar el espaciado horizontal
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BlockDSW(
            text = "DSW",
            modifier = Modifier
                .width(40.dp)
                .height(120.dp)
        )
    }
}

@Composable
fun BlockDSW(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFFF7878))
            .border(
                width = 1.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 8.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BlockGridSoccer(
    modifier: Modifier = Modifier,
    offsetX: Dp = 0.dp,
    horizontalSpacing: Dp = 8.dp // Espacio horizontal entre bloques
) {
    Row(
        modifier = modifier
            .offset(x = offsetX)
            .padding(horizontal = horizontalSpacing), // Aplicar el espaciado horizontal
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BlockSoccer(
            text = "Soccer",
            modifier = Modifier
                .width(40.dp)
                .height(120.dp)
        )
    }
}

@Composable
fun BlockSoccer(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF78BEFF))
            .border(
                width = 1.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 8.sp,
            fontWeight = FontWeight.Bold
        )
    }
}