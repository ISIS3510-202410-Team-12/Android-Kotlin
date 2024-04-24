package com.example.kotlin

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun GroupsScreen() {

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val context = LocalContext.current
            val notificationService = remember { NotificationService(context) }
            val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    notificationService.showNotification()
                }
            }
            Button(onClick = {
                launcher.launch(android.Manifest.permission.ACCESS_NOTIFICATION_POLICY)
            }) {
                Text("Show Notification")
            }
        }
    }
}

/** se deja el codigo documentado para que laura cambie la parte del notify **/

/**
 * package com.example.kotlin
 *
 * import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun GroupsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val context = LocalContext.current
            val notificationService = remember { NotificationService(context) }
            val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    notificationService.showNotification()
                }
            }
            Button(onClick = {
                launcher.launch(android.Manifest.permission.ACCESS_NOTIFICATION_POLICY)
            }) {
                Text("Show Notification")
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    val groups = listOf(
        ElementGroups("Group 1", Color(0xFFFF7648), Color(0xFFFFC278), R.drawable.ic_user_group, 150, listOf(R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3)),
        ElementGroups("Group 2", Color(0xFF8F98FF), Color(0xFF182A88), R.drawable.ic_user_group, 150, listOf(R.drawable.avatar4, R.drawable.avatar5)),
        ElementGroups("Group 3", Color(0xFFFF8FB7), Color(0xFFFDE0E0), R.drawable.ic_user_group, 150, listOf(R.drawable.avatar6, R.drawable.avatar7, R.drawable.avatar8)),
        ElementGroups("Group 4", Color(0xFFFF7648), Color(0xFFFF8FB7), R.drawable.ic_user_group, 150, listOf(R.drawable.avatar9, R.drawable.avatar10)),
        ElementGroups("Group 5", Color(0xFFFF7648), Color(0xFFFF8FB7), R.drawable.ic_user_group, 150, listOf(R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3))
    )

    var query by remember { mutableStateOf("") }

    SearchBar(
        query = query,
        onQueryChange = { query = it }
    )

    Spacer(modifier = Modifier.height(16.dp))

    if (query.isNotEmpty()) {
        val filteredGroups = groups.filter { it.name.contains(query, true) }
        filteredGroups.forEach { group ->
            ElementBoxGroups(group)
        }
    } else {
        groups.forEach { group ->
            ElementBoxGroups(group)
        }
    }
}
}

@Composable
fun TopButtonsGroups() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { /TODO/ },
            modifier = Modifier.padding(6.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bars),
                tint = MaterialTheme.colors.background,
                contentDescription = "Burger",
                modifier = Modifier.size(25.dp)
            )
        }

        Text(
            text = "Groups",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        IconButton(
            onClick = { /TODO/ },
            modifier = Modifier.padding(6.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bell),
                tint = MaterialTheme.colors.background,
                contentDescription = "Notifications",
                modifier = Modifier.size(25.dp)
            )
        }
    }
}

@Composable
fun TitleGroups(text: String) {
    Text(
        modifier = Modifier.padding(start = 26.dp),
        text = text,
        color = Color(0xFF000000),
        style = MaterialTheme.typography.titleMedium)
}

@Composable
fun ElementBoxesGroups(type: String, elements:  List<Element>) {
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
fun ElementBoxGroups(element: ElementGroups) {
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
            UserAvatars(element.userAvatars)
            Text(
                text = "${element.name}",
                modifier = Modifier.padding(start = 10.dp, top = 10.dp)
            )
        }
    }
}

data class ElementGroups(
    val name: String,
    val primaryColor: Color,
    val secondaryColor: Color,
    val icon: Int,
    val width: Int,
    val userAvatars: List<Int>
)

val groupsGroups = listOf(
    ElementGroups("Group 1", Color(0xFFFF7648), Color(0xFFFFC278), R.drawable.ic_user_group, 150, listOf(R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3)),
    ElementGroups("Group 2", Color(0xFF8F98FF), Color(0xFF182A88), R.drawable.ic_user_group, 150, listOf(R.drawable.avatar4, R.drawable.avatar5)),
    ElementGroups("Group 3", Color(0xFFFF8FB7), Color(0xFFFDE0E0), R.drawable.ic_user_group, 150, listOf(R.drawable.avatar6, R.drawable.avatar7, R.drawable.avatar8)),
    ElementGroups("Group 4", Color(0xFFFF7648), Color(0xFFFF8FB7), R.drawable.ic_user_group, 150, listOf(R.drawable.avatar9, R.drawable.avatar10)),
    ElementGroups("Group 5", Color(0xFFFF7648), Color(0xFFFF8FB7), R.drawable.ic_user_group, 150, listOf(R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3))
)

@Composable
fun UserAvatars(userAvatars: List<Int>) {
    Row(
        modifier = Modifier
            .padding(start = 10.dp, top = 10.dp)
            .size(40.dp),
        horizontalArrangement = Arrangement.spacedBy((-10).dp)
    ) {
        userAvatars.forEachIndexed { index, avatarResId ->
            Image(
                painter = painterResource(id = avatarResId),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape)
                    .offset(x = index * 20.dp)
            )
        }
    }
}
 */