package com.example.kotlin

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlin.ui.theme.KotlinTheme

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the screen orientation to portrait
        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT.also { requestedOrientation = it }
        setContent {
            KotlinTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val authSuccessful = rememberSaveable { mutableStateOf(false) }
                    if (authSuccessful.value) {
                        NavigationBar()
                    } else {
                        Auth(
                            //onAuthSuccess = { authSuccessful.value = true }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Auth() {

    var auth by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier.background(Color.Red)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Auth Screen", fontSize = 22.sp)

        Spacer(Modifier.height(8.dp))

        Button(onClick = {

        }) {
            Text(text = "Login")
        }
    }
}



sealed class Screens (val screen: String) {
    data object HomeScreen: Screens("home")
    data object CalendarScreen: Screens("calendar")
    data object FriendsScreen: Screens("friends")
    data object GroupsScreen: Screens("groups")
}

data class NavItem (
    val icon: Int,
    val label: String,
    val screen: String
)

val navItems = listOf(
    NavItem(R.drawable.ic_house, "Home", Screens.HomeScreen.screen),
    NavItem(R.drawable.ic_calendar, "Calendar", Screens.CalendarScreen.screen),
    NavItem(R.drawable.ic_address_book, "Friends", Screens.FriendsScreen.screen),
    NavItem(R.drawable.ic_user_group, "Groups", Screens.GroupsScreen.screen)
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationBar() {
    val navigationController = rememberNavController()
    val selected = remember { mutableIntStateOf(R.drawable.ic_house) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            BottomAppBar (
                containerColor = MaterialTheme.colorScheme.onBackground,
            ){
                navItems.forEach { item ->
                    IconButton(
                        onClick = {
                            selected.intValue = item.icon
                            navigationController.navigate(item.screen) {
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier.weight(1f).fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.label,
                                modifier = Modifier.size(26.dp),
                                tint = if (selected.intValue == item.icon) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                            )
                            Text(
                                text = item.label,
                                color = if (selected.intValue == item.icon) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navigationController,
            startDestination = Screens.HomeScreen.screen,
            modifier = Modifier.padding(paddingValues),
            enterTransition = { -> fadeIn(animationSpec = tween(200)) },
            exitTransition = { -> fadeOut(animationSpec = tween(200)) }
        ){
            composable(Screens.HomeScreen.screen){HomeScreen()}
            composable(Screens.CalendarScreen.screen){CalendarScreen()}
            composable(Screens.FriendsScreen.screen){FriendsScreen()}
            composable(Screens.GroupsScreen.screen){GroupsScreen()}
        }
    }
}