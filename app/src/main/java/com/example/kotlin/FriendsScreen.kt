package com.example.kotlin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kotlin.ui.theme.KotlinTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn


data class Friend(val name: String, val avatarResId: Int)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendsScreen() {
    val ctx = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopButtons()
        Spacer(modifier = Modifier.height(16.dp))
        var query by remember { mutableStateOf("") }
        var active by remember { mutableStateOf(false) }

        SearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearch = {
                Toast.makeText(ctx, query, Toast.LENGTH_SHORT).show()
                active = false
            },
            active = active,
            onActiveChange = { active = it }
        ) {
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Sample list of friends
        val friends = listOf(
            Friend("Friend 1", R.drawable.sticker),
            Friend("Friend 2", R.drawable.sticker),
            Friend("Friend 3", R.drawable.sticker),
            Friend("Friend 4", R.drawable.sticker),
            Friend("Friend 5", R.drawable.sticker),
            Friend("Friend 6", R.drawable.sticker),
            Friend("Friend 7", R.drawable.sticker),
            Friend("Friend 8", R.drawable.sticker),
            Friend("Friend 9", R.drawable.sticker),
            Friend("Friend 10", R.drawable.sticker)
        )
        // Filtering the list of friends based on the query
        if(query.isNotEmpty()){
            val filteredFriends = friends.filter { it.name.contains(query, true) }
            filteredFriends.forEach { (name, avatarResId) ->
                Text(
                    text ="$name $avatarResId",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            Toast
                                .makeText(ctx, name, Toast.LENGTH_SHORT)
                                .show()
                            active = false
                        }
                )
            }
        }
        FriendList(
            friends = friends
        )
    }
}

@Composable
fun TopButtonsFriends() {
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
            text = "Friends",
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
fun FriendList(friends: List<Friend>) {
    LazyColumn {
        items(friends) { friend ->
            FriendItem(friend = friend)
        }
    }
}

@Composable
fun FriendItem(friend: Friend) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = friend.avatarResId),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = friend.name,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}