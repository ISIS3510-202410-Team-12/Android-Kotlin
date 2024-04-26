package com.example.kotlin

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.kotlin.Helper.ConnectionStatus
import com.example.kotlin.Helper.currentConnectivityStatus
import com.example.kotlin.Helper.observeConnectivityAsFlow
import com.example.kotlin.datastore.SQLiteHelperGroups
import kotlinx.coroutines.ExperimentalCoroutinesApi

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun GroupsScreen() {
    val ctx = LocalContext.current
    val sqliteHelperGroups = SQLiteHelperGroups(ctx) // Create an instance of SQLiteHelperFriends
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopButtonsGroups()
            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier.height(16.dp))
            GroupBoxes("Group", group_list)
        }
    }
}

val group_list = listOf(
    Group("Group 1", Color(0xFFFF7648), Color(0xFFFFC278), R.drawable.ic_user_group, 150, listOf(R.drawable.ic_circle_user, R.drawable.ic_circle_user, R.drawable.ic_circle_user)),
    Group("Group 2", Color(0xFF8F98FF), Color(0xFF182A88), R.drawable.ic_user_group, 150, listOf(R.drawable.ic_circle_user, R.drawable.ic_circle_user)),
    Group("Group 3", Color(0xFFFF8FB7), Color(0xFFFDE0E0), R.drawable.ic_user_group, 150, listOf(R.drawable.ic_circle_user, R.drawable.ic_circle_user, R.drawable.ic_circle_user)),
    Group("Group 4", Color(0xFFFF7648), Color(0xFFFF8FB7), R.drawable.ic_user_group, 150, listOf(R.drawable.ic_circle_user, R.drawable.ic_circle_user)),
    Group("Group 5", Color(0xFFFF7648), Color(0xFFFF8FB7), R.drawable.ic_user_group, 150, listOf(R.drawable.ic_circle_user, R.drawable.ic_circle_user, R.drawable.ic_circle_user))
)

@Composable
fun TopButtonsGroups() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
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

        Text(
            text = "Groups",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        IconButton(
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
fun TitleGroups(text: String) {
    Text(
        modifier = Modifier.padding(start = 26.dp),
        text = text,
        color = Color(0xFF000000),
        style = MaterialTheme.typography.titleMedium)
}

@Composable
fun GroupBoxes(type: String, elements: List<Group>) {
    val scrollState = rememberScrollState()

    Title("My $type" + "s")
    Column (
        modifier = Modifier
            .horizontalScroll(scrollState)
            .padding(start = 26.dp, top = 10.dp, bottom = 16.dp)
    ) {
        elements.forEach {
            GroupBox(it)
        }
    }
}

@Composable
fun GroupBox(element: Group) {
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
                text = element.name,
                modifier = Modifier.padding(start = 10.dp, top = 10.dp)
            )
        }
    }
}

data class Group(
    val name: String,
    val primaryColor: Color,
    val secondaryColor: Color,
    val icon: Int,
    val width: Int,
    val userAvatars: List<Int>
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

@ExperimentalCoroutinesApi
@Composable
fun connectivityStatusGroup(): State<ConnectionStatus> {
    val mCtx = LocalContext.current

    return produceState(initialValue = mCtx.currentConnectivityStatus) {
        mCtx.observeConnectivityAsFlow().collect { value = it }
    }
}




@ExperimentalCoroutinesApi
@Composable
fun checkConnectivityStatusGroup(groups: List<Group>, sqliteHelperGroups: SQLiteHelperGroups){

    val connection by connectivityStatus()

    val isConnected = connection === ConnectionStatus.Available

    if(isConnected)
    {
        /**lo que hace cuando no esta connectado **/
        // Fetch friends data from the server when the composable is first created
    }
    else
    {
        /**lo que hace cuando no esta conectado **/
        // Save the list of groups to the database
        LazyColumn {
            items(groups) { group ->
                GroupBox(group)
                sqliteHelperGroups.insertGroup(group.name, group.userAvatars.joinToString()) // Inserta el grupo en la base de datos
            }
            UserAvatars(element.userAvatars)
            Text(
                text = element.name,
                modifier = Modifier.padding(start = 10.dp, top = 10.dp)
            )
        }
    }
}

data class Group(
    val name: String,
    val primaryColor: Color,
    val secondaryColor: Color,
    val icon: Int,
    val width: Int,
    val userAvatars: List<Int>
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