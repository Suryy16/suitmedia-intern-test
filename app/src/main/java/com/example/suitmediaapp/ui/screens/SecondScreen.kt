package com.example.suitmediaapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.suitmediaapp.R
import com.example.suitmediaapp.viewmodel.UserViewModel

/**
 * SecondScreen is a composable function that displays the second screen of the application.
 * It includes a top app bar with a back button, a welcome message, the selected user's name,
 * and a button to navigate to the third screen.
 *
 * @param navController The NavController used for navigation between screens.
 * @param viewModel The UserViewModel used to manage user-related data.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(navController: NavController, viewModel: UserViewModel) {
    val selectedUser by viewModel.selectedUser.collectAsState()
    val name by viewModel.name.collectAsState()

    Scaffold(
        topBar = {
            Column {
                Spacer(modifier = Modifier.height(8.dp))

                TopAppBar(
                    title = {
                        Box(modifier = Modifier.fillMaxWidth().padding(end = 50.dp), contentAlignment = Alignment.Center) {
                            Text(
                                text = "Second Screen",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 24.sp
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = "Back",
                                modifier = Modifier.size(50.dp)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Divider(
                    color = Color.LightGray.copy(alpha = 0.5f),
                    thickness = 1.dp
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(40.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart)
            ) {
                // Welcome Section
                Text(
                    text = "Welcome",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 27.sp
                    )
                )
            }

            // Centered Selected User Name
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .padding(32.dp)
            ) {
                Text(
                    text = selectedUser?.let { "${it.first_name} ${it.last_name}" } ?: "Selected User Name",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 30.sp
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            // Button at the bottom
            Button(
                onClick = { navController.navigate("third_screen") },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2B637B),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "Choose a User", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}