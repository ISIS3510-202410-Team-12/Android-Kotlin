package com.example.kotlin

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kotlin.ui.theme.KotlinTheme

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun AddEventScreen(navController: NavController) {
    KotlinTheme {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopBar(navController)
            RecurrenceButtons()
            Form()
            Notification()
        }
    }
}

@Composable
fun ShowDialog() {
    val showDialog = remember { mutableStateOf(true) }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showDialog.value = false
            },
            title = {
                Text(text = "Dialog Title")
            },
            text = {
                Text("This is a simple dialog")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog.value = false
                        // Handle confirm button action here
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog.value = false
                        // Handle cancel button action here
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun TopBar(navController: NavController) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton (
            onClick = {
                navController.popBackStack(Screens.CalendarScreen.screen, false)
            },
            modifier = Modifier.padding(6.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                tint = Color.Black,
                contentDescription = "Go back",
                modifier = Modifier.size(25.dp)
            )
        }
        Text(
            text = "Create Class",
            color = Color.Black,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(6.dp)
        )
        IconButton (
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(6.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                tint = Color.Black,
                contentDescription = "Notifications",
                modifier = Modifier.size(25.dp)
            )
        }
    }
}

@Composable
fun RecurrenceButtons() {
    val selectedButton = remember { mutableStateOf("Recurrent") }

    Row {
        Button(
            onClick = { selectedButton.value = "One-Time" },
            colors = ButtonDefaults.buttonColors(
                if (selectedButton.value == "One-Time") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
            )
        ) {
            Text(
                text = "One-Time",
                color = if (selectedButton.value == "Recurrent") Color.DarkGray else MaterialTheme.colorScheme.onBackground
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            onClick = { selectedButton.value = "Recurrent" },
            colors = ButtonDefaults.buttonColors(
                if (selectedButton.value == "Recurrent") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
            ),
        ) {
            Text(
                text = "Recurrent",
                color = if (selectedButton.value == "One-Time") Color.DarkGray else MaterialTheme.colorScheme.onBackground
                )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Form(){
    var eventName by remember { mutableStateOf(TextFieldValue()) }
    var assistants by remember { mutableStateOf(TextFieldValue()) }
    var reminder by remember { mutableStateOf(TextFieldValue()) }
    var labels by remember { mutableStateOf(TextFieldValue()) }

    Surface (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(25.dp)),
        color = MaterialTheme.colorScheme.onBackground,
        shape = RoundedCornerShape(25.dp)

    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ){
            TextField(
                value = eventName,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { newText ->
                    eventName = newText
                },
                label = { Text("Event Name") },

                trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_signature),
                            tint = Color.Black,
                            contentDescription = "Calendar",
                            modifier = Modifier.size(25.dp)
                        )
                },
                colors = textFieldColors(
                    containerColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedIndicatorColor = Color.LightGray
                )
            )
            TextField(
                value = assistants,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { newText ->
                    assistants = newText
                },
                label = { Text("Assistants") },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_person_chalkboard),
                        tint = Color.Black,
                        contentDescription = "Calendar",
                        modifier = Modifier.size(25.dp)
                    )
                },
                colors = textFieldColors(
                    containerColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedIndicatorColor = Color.LightGray
                )
            )
            TextField(
                value = reminder,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { newText ->
                    reminder = newText
                },
                label = { Text("Reminder") },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_stopwatch),
                        tint = Color.Black,
                        contentDescription = "Calendar",
                        modifier = Modifier.size(25.dp)
                    )
                },
                colors = textFieldColors(
                    containerColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedIndicatorColor = Color.LightGray
                )
            )
            TextField(
                value = labels,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { newText ->
                    labels = newText
                },
                label = { Text("Labels") },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_tag),
                        tint = Color.Black,
                        contentDescription = "Calendar",
                        modifier = Modifier.size(25.dp)
                    )
                },
                colors = textFieldColors(
                    containerColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
                    focusedIndicatorColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    }
}

@Composable
fun Details(){

}

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun Notification(){
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

@RequiresApi(Build.VERSION_CODES.M)
@Preview(showSystemUi = true)
@Composable
fun PreviewAddEventScreen() {
    AddEventScreen(navController = rememberNavController())
}