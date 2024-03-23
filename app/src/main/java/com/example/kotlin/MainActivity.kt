package com.example.kotlin

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlin.ui.theme.KotlinTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT.also { requestedOrientation = it }
        setupAuth()

        setContent {
            val isAuthenticated = rememberSaveable { mutableStateOf(false) }

            KotlinTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (isAuthenticated.value) {
                        NavigationBar()
                    } else {
                        Auth(
                            onAuthSuccess = { isAuthenticated.value = true }
                        )
                    }
                }
            }
        }
    }

    private var canAuthenticate = false
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private fun setupAuth() {
        // Setup authentication
        if (BiometricManager.from(this).canAuthenticate(
                BiometricManager.Authenticators.BIOMETRIC_STRONG
                        or BiometricManager.Authenticators.DEVICE_CREDENTIAL
            ) == BiometricManager.BIOMETRIC_SUCCESS
        ) {
            canAuthenticate = true

            promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for my app")
                .setSubtitle("Log in using your biometric credential")
                .setAllowedAuthenticators(
                    BiometricManager.Authenticators.BIOMETRIC_STRONG
                            or BiometricManager.Authenticators.DEVICE_CREDENTIAL
                )
                .build()
        }
    }

    private fun authenticate(auth: (auth: Boolean) -> Unit) {
        if (canAuthenticate) {
            BiometricPrompt(this, ContextCompat.getMainExecutor(this),
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        auth(true)
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        auth(false)
                    }
                }).authenticate(promptInfo)
        }
    }

    @Composable
    fun Auth(
        onAuthSuccess: () -> Unit
    ) {
        var auth by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.bg),
                    contentScale = ContentScale.Crop
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "UniSchedule", fontSize = 22.sp, color = MaterialTheme.colorScheme.onBackground)

                Spacer(Modifier.height(20.dp))

                Text(
                    text = "Welcome Laura!",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 30.sp,
                        shadow = Shadow(
                            color = Color(0xFF000000),
                            offset = Offset(10f, 10f),
                            blurRadius = 15f
                        )
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(Modifier.height(500.dp))

                Button(
                    //modifier = Modifier.background(Color(0xFF1A73E8)),
                    onClick = {
                        authenticate { success ->
                            auth = success
                            if (success) {
                                onAuthSuccess()
                            }
                        }
                    }
                ) {
                    Text(text = "Biometric Login")
                }
            }
        }
    }
}
sealed class Screens(val screen: String) {
    data object HomeScreen : Screens("home")
    data object CalendarScreen : Screens("calendar")
    data object FriendsScreen : Screens("friends")
    data object GroupsScreen : Screens("groups")
}

data class NavItem(
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
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.onBackground,
            ) {
                navItems.forEach { item ->
                    IconButton(
                        onClick = {
                            selected.intValue = item.icon
                            navigationController.navigate(item.screen) {
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
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
            enterTransition = { fadeIn(animationSpec = tween(200)) },
            exitTransition = { fadeOut(animationSpec = tween(200)) }
        ) {
            composable(Screens.HomeScreen.screen) { HomeScreen() }
            composable(Screens.CalendarScreen.screen) { CalendarScreen() }
            composable(Screens.FriendsScreen.screen) { FriendsScreen() }
            composable(Screens.GroupsScreen.screen) { GroupsScreen() }
        }
    }
}