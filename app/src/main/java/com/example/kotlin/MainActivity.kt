package com.example.kotlin

import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kotlin.ui.theme.KotlinTheme
import com.example.kotlin.ui.theme.poppinsFontFamily


class MainActivity : AppCompatActivity() {
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
                        Column(horizontalAlignment = Alignment.CenterHorizontally) { // Agregado Column para permitir el texto debajo del ícono
                            Icon(
                                painter = painterResource(id = destinations.selectedIcon),
                                contentDescription = stringResource(id = destinations.iconTextId),
                                modifier = Modifier.size(24.dp), // Ajusta el tamaño según tus necesidades
                                tint = if (selectedDestination == destinations.route) Color(
                                    android.graphics.Color.parseColor(
                                        "#9dcc18"
                                    )
                                ) else Color(android.graphics.Color.parseColor("#9fa5c0"))
                            )
                            Text(
                                stringResource(id = destinations.iconTextId),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = if (selectedDestination == destinations.route) Color(
                                        android.graphics.Color.parseColor(
                                            "#9dcc18"
                                        )
                                    ) else Color(android.graphics.Color.parseColor("#9fa5c0")),
                                    fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold
                                )
                            ) // Agregado Text debajo del ícono
                        }
                    }
                )
            }
        }
    }
}

