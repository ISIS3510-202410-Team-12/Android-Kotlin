package com.example.kotlin

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp

private const val userName: String = "Laura"

@Composable
fun HomeScreen() {
    Box {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.6f)
        ) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "Background",
            alignment = Alignment.TopStart,
            contentScale = ContentScale.Crop,
            alpha = 0.75f
        )
    }
        Column (
            modifier = Modifier.fillMaxSize()
        ) {
            TopButtons()
            Column(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                UserGreeting(userName)
                Column (
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    WhiteSpace()
                }

            }
        }
    }
}


@Composable
fun TopButtons() {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton (
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(6.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bars),
                tint = MaterialTheme.colorScheme.background,
                contentDescription = "Burger",
                modifier = Modifier.size(25.dp)
            )
        }
        IconButton (
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(6.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bell),
                tint = MaterialTheme.colorScheme.background,
                contentDescription = "Notifications",
                modifier = Modifier.size(25.dp)
            )
        }
    }
}

@Composable
fun UserGreeting(name: String) {
    Box (
        modifier = Modifier
            .padding(start = 26.dp, top = 15.dp)
    ){
        Text(
            text = "Hello, $name!",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 45.sp,
                shadow = Shadow(
                    color = Color(0xFF000000),
                    offset = Offset(10f, 10f),
                    blurRadius = 15f
                )
            ),
        )
        Text(
            text = "Hello, $name!",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 45.sp,
                color = Color(0xFF000000),
                drawStyle = Stroke(
                    miter = 10f,
                    width = 5f,
                    join = StrokeJoin.Round
                ),
            )
        )
    }
}

@Composable
fun Title(text: String) {
    Text(
        modifier = Modifier.padding(start = 26.dp),
        text = text,
        color = Color(0xFF000000),
        style = MaterialTheme.typography.titleMedium)
}

@Composable
fun ElementBoxes(type: String, elements:  List<Element>) {

    val scrollState = rememberScrollState()

    Title("My $type" + "s")
    Row (
        modifier = Modifier
            .horizontalScroll(scrollState)
            .padding(start = 26.dp, top = 10.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        elements.forEach {
            ElementBox(it)
        }
    }
}

@Composable
fun ElementBox(element: Element) {
    Surface(
        modifier = Modifier
            .height(100.dp)
            .width(element.width.dp)
            .padding(1.dp),
        color = element.primaryColor,
        shape = RoundedCornerShape(20.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_splotch),
            contentDescription = "Splotch",
            tint = element.secondaryColor,
            modifier = Modifier
                .size(20.dp)
                .offset(x = 80.dp, y = (-50).dp)
        )
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp, end = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Icon(
                    painter = painterResource(id = element.icon),
                    contentDescription = "Icon",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .size(20.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_ellipsis_vertical),
                    contentDescription = "Icon",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .size(20.dp)
                )
            }
            Text(text = "${element.name} ",
                modifier = Modifier
                    .padding(start = 10.dp,  top = 10.dp),
            )
        }
    }
}

data class Element (
    val name: String,
    val primaryColor: Color,
    val secondaryColor: Color,
    val icon: Int,
    val width: Int,
)

val groups = listOf(
    Element("Group 1", Color(0xFFFF7648), Color(0xFFFFC278), R.drawable.ic_user_group, 150),
    Element("Group 2", Color(0xFF8F98FF), Color(0xFF182A88), R.drawable.ic_user_group, 150),
    Element("Group 3", Color(0xFFFF8FB7), Color(0xFFFDE0E0), R.drawable.ic_user_group, 150),
    Element("Group 4", Color(0xFFFF7648), Color(0xFFFF8FB7), R.drawable.ic_user_group, 150),
    Element("Group 5", Color(0xFFFF7648), Color(0xFFFF8FB7), R.drawable.ic_user_group, 150),
)

val events = listOf(
    Element("Event 1", Color(0xFF78BEFF), Color(0xFF8F98FF), R.drawable.ic_calendar, 200),
    Element("Event 2", Color(0xFFFF6961), Color(0xFFC23B22), R.drawable.ic_calendar, 200),
    Element("Event 3", Color(0xFF8F98FF), Color(0xFF8F98FF), R.drawable.ic_calendar, 200),
)

@Composable
fun WhiteSpace() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    )
    {
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.65f),
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(topStart = 40.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp),
                ) {
                    ElementBoxes("Group", groups)
                    ElementBoxes("Event", events)
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.sticker),
            contentDescription = "Icon",
            alignment = Alignment.BottomEnd,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(220.dp)
                .padding(end = 16.dp)
                .offset(y = (15).dp)
        )
    }
}