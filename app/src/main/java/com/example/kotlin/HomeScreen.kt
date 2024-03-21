package com.example.kotlin

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlin.ui.theme.KotlinTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.sp

private const val userName: String = "Laura"

@Composable
fun HomeScreen() {
    Box {

        Column(modifier = Modifier.fillMaxSize()) {
            TopButtons()
            UserGreeting(userName)
            Elements("Group")
            Elements("Event")
        }
    }
}


@Composable
fun TopButtons() {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {}
        ) {
            Text("Burger")
        }
        Button(
            onClick = {}
        ) {
            Text("Notif")
        }
    }
}

@Composable
fun UserGreeting(name: String) {
    Box {
        Text(
            text = "Hola, $name!",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 40.sp,
            color = Color(0xFF000000)
        )
        Text(
            text = "Hola, $name!",
            fontSize = 40.sp,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 40.sp,
                drawStyle = Stroke(
                    miter = 10f,
                    width = 5f,
                    join = StrokeJoin.Round
                )

            )
        )
    }
}

@Composable
fun Title(text: String) {
    Text(text)
}

@Composable
fun Elements(element: String) {

    val scrollState = rememberScrollState()

    Title("My $element" + "s")
    Row (modifier = Modifier.horizontalScroll(scrollState)) {
        Element("$element 1", Color(0xFFFF7648))
        Element("$element 2", Color(0xFF8F98FF))
        Element("$element 3", Color(0xFFFF8FB7))
    }
}

@Composable
fun Element(type: String, color: Color) {
    Surface(
        modifier = Modifier
            .height(150.dp)
            .width(250.dp)
            .padding(1.dp)/*Padding for surface*/,
        color = color,
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(text = "$type ",Modifier.padding(16.dp)/*Padding for Text*/)
    }
}




@Preview(showSystemUi = true)
@Composable
fun PreviewView() {
    KotlinTheme {
        HomeScreen()
    }
}