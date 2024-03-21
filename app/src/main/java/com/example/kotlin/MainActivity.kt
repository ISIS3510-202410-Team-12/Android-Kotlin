package com.example.kotlin

import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kotlin.ui.theme.KotlinTheme


class MainActivity : ComponentActivity() {
    @RequiresApi(VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinTheme {
                val navController = rememberNavController()
                val navigateAction = remember(navController) {
                    MyAppNavigationActions(navController)
                }
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val selectedDestination = navBackStackEntry?.destination?.route ?: MyAppRoute.HOME

                MyAppContent(
                    navController = navController,
                    selectedDestination = selectedDestination,
                    navigateTopLevelDestination = navigateAction::navigateTo
                )
            }
        }
    }

    @RequiresApi(VERSION_CODES.O)
    @Composable
    fun MyAppContent(
        modifier: Modifier = Modifier,
        navController: NavHostController,
        selectedDestination: String,
        navigateTopLevelDestination: (MyAppTopLevelDestination) -> Unit
    ) {
        Row(modifier = modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                NavHost(
                    modifier = Modifier.weight(1f),
                    navController = navController,
                    startDestination = MyAppRoute.HOME
                ) {
                    composable(MyAppRoute.HOME) {
                        HomeScreen()
                    }
                    composable(MyAppRoute.CALENDAR) {
                        CalendarScreen()
                    }
                    composable(MyAppRoute.FRIENDS) {
                        FriendScreen()
                    }
                    composable(MyAppRoute.GROUP) {
                        GroupScreen()
                    }
                }
                MyAppBottomNavigation(
                    selectedDestination = selectedDestination,
                    navigateTopLevelDestination = navigateTopLevelDestination
                )
            }
        }
    }


    @Composable
    fun MyAppBottomNavigation(
        selectedDestination: String,
        navigateTopLevelDestination: (MyAppTopLevelDestination) -> Unit
    ) {
        NavigationBar(modifier = Modifier.fillMaxWidth()) {
            TOP_LEVEL_DESTINATIONS.forEach { destinations ->
                NavigationBarItem(
                    selected = selectedDestination == destinations.route,
                    onClick = { navigateTopLevelDestination(destinations) },
                    icon = {
                        Icon(
                            imageVector = destinations.selectedIcon,
                            contentDescription = stringResource(id = destinations.iconTextId)
                        )
                    })
            }
        }

    }
}


