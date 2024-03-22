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
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
            Icon(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = "Add",
                modifier = Modifier
                    .size(24.dp) //
            )
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
                BarsIconButton(
                    onClick = { /*TODO: Implement the logic for the button click*/ }
                )
                Text(
                    text = LocalDate.now().month.getDisplayName(TextStyle1.FULL, Locale.getDefault()),
                    style = TextStyle(fontSize = 24.sp, color = Color.White,  fontWeight = FontWeight.SemiBold),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.offset(x = -90.dp, y = 10.dp) // Desplazamiento horizontal de 20 píxeles
                )
                BellIconButton(
                    onClick = { /*TODO: Implement the logic for the button click*/ }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(7) { day ->
                    Text(
                        text = getDayOfWeekAbbreviation(day),
                        style = TextStyle(fontSize = 16.sp, color = Color.White,  fontWeight = FontWeight.Bold)
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
                        style = TextStyle(fontSize = 16.sp, color = Color.White,  fontWeight = FontWeight.Bold)
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
                .height(80.dp)
                .offset(y = (-148).dp),
            offsetX = 5.dp // Ahora es 50.dp en lugar de solo 50

        )
        BlockGridLym(
            modifier = Modifier
                .align(Alignment.Center)
                .width(200.dp)
                .height(90.dp)
                .offset(y = (-42).dp),
            offsetX = 0.dp,
            horizontalSpacing = 13.dp // Espacio horizontal entre bloques en BlockGridLym
        )
        BlockGridDSW(
            modifier = Modifier
                .align(Alignment.Center)
                .width(200.dp)
                .height(90.dp)
                .offset(y = (159).dp),
            offsetX = 6.dp,
            horizontalSpacing = 13.dp // Espacio horizontal entre bloques en BlockGridLym
        )
        BlockGridSoccer(
            modifier = Modifier
                .align(Alignment.Center)
                .width(200.dp)
                .height(90.dp)
                .offset(y = (159).dp),
            offsetX = 200.dp,
            horizontalSpacing = 13.dp // Espacio horizontal entre bloques en BlockGridLym
        )

    }
}

@Composable
fun BellIconButton(
    onClick: () -> Unit
) {
    val bellIcon = painterResource(id = R.drawable.ic_bell)
    IconButton(
        onClick = onClick,
        content = {
            Icon(
                painter = bellIcon,
                contentDescription = "Bell Icon",
                tint = Color.White // You can change the tint color if desired
            )
        }
    )
}

@Composable
fun BarsIconButton(
    onClick: () -> Unit
) {
    val barsIcon = painterResource(id = R.drawable.ic_bars)
    IconButton(
        onClick = onClick,
        content = {
            Icon(
                painter = barsIcon,
                contentDescription = "Bell Icon",
                tint = Color.White // You can change the tint color if desired
            )
        }
    )
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
                Text(text = if (hour < 10) "0$hour:00" else "$hour:00", fontSize = 12.sp, color = Color.White,  fontWeight = FontWeight.SemiBold)
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
            text1 = "8:00",
            text2 = "MOS",
            text3 = "9:50",
            modifier = Modifier
                .width(43.dp)
                .height(120.dp)
        )
        Block(
            text1 = "8:00",
            text2 = "MOS",
            text3 = "9:50",
            modifier = Modifier
                .width(43.dp)
                .height(120.dp)
        )
        Block(
            text1 = "8:00",
            text2 = "MOS",
            text3 = "9:50",
            modifier = Modifier
                .width(43.dp)
                .height(120.dp)
        )
    }
}

@Composable
fun Block(
    text1: String,
    text2: String,
    text3: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF8F98FF).copy(alpha = 0.6f))
            .border(
                width = 1.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(10.dp)
    ) {
        Text(
            text = text1,
            color = Color.White,
            fontSize = 5.sp,
             fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.TopStart).padding(1.dp)
                .offset(x = -10.dp, y = -18.dp)
        )
        Text(
            text = text2,
            color = Color.White,
            fontSize = 8.sp,
             fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.Center)
        )
        Text(
            text = text3,
            color = Color.White,
            fontSize = 5.sp,
             fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.BottomEnd).padding(1.dp)
                .offset(x = 10.dp, y = 18.dp)
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
            text1 = "10:00",
            text2 = "Lym",
            text3 = "11:30",
            modifier = Modifier
                .width(43.dp)
                .height(120.dp)
        )
        BlockLym(
            text1 = "10:00",
            text2 = "Lym",
            text3 = "11:30",
            modifier = Modifier
                .width(43.dp)
                .height(120.dp)
        )
    }
}

@Composable
fun BlockLym(
    text1: String,
    text2: String,
    text3: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFFF7648).copy(alpha = 0.6f))
            .border(
                width = 1.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(10.dp)
    ) {
        Text(
            text = text1,
            color = Color.White,
            fontSize = 5.sp,
             fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.TopStart).padding(1.dp)
                .offset(x = -10.dp, y = -18.dp)
        )
        Text(
            text = text2,
            color = Color.White,
            fontSize = 8.sp,
             fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.Center)
        )
        Text(
            text = text3,
            color = Color.White,
            fontSize = 5.sp,
             fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.BottomEnd).padding(1.dp)
                .offset(x = 10.dp, y = 18.dp)
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
            text1 = "14:00",
            text2 = "DSW",
            text3 = "15:30",
            modifier = Modifier
                .width(43.dp)
                .height(120.dp)
        )
    }
}

@Composable
fun BlockDSW(
    text1: String,
    text2: String,
    text3: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFFF7878).copy(alpha = 0.6f))
            .border(
                width = 1.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(10.dp)
    ) {
        Text(
            text = text1,
            color = Color.White,
            fontSize = 5.sp,
             fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.TopStart).padding(1.dp)
                .offset(x = -10.dp, y = -18.dp)
        )
        Text(
            text = text2,
            color = Color.White,
            fontSize = 8.sp,
             fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.Center)
        )
        Text(
            text = text3,
            color = Color.White,
            fontSize = 5.sp,
             fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.BottomEnd).padding(1.dp)
                .offset(x = 10.dp, y = 18.dp)
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
            text1 = "14:00",
            text2 = "Soccer",
            text3 = "15:30",
            modifier = Modifier
                .width(43.dp)
                .height(120.dp)
        )
    }
}

@Composable
fun BlockSoccer(
    text1: String,
    text2: String,
    text3: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF78BEFF).copy(alpha = 0.6f))
            .border(
                width = 1.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(10.dp)
    ) {
        Text(
            text = text1,
            color = Color.White,
            fontSize = 5.sp,
             fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.TopStart).padding(1.dp)
                .offset(x = -10.dp, y = -17.dp)
        )
        Text(
            text = text2,
            color = Color.White,
            fontSize = 8.sp,
             fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.Center)
        )
        Text(
            text = text3,
            color = Color.White,
            fontSize = 5.sp,
             fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.BottomEnd).padding(1.dp)
                .offset(x = 10.dp, y = 18.dp)
        )
    }
}