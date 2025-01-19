package com.example.suitmediaapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.suitmediaapp.R
import com.example.suitmediaapp.viewmodel.UserViewModel


/**
 * FirstScreen is a composable function that displays the first screen of the application.
 * It includes a background image, user icon, input fields for name and palindrome check,
 * and buttons to check palindrome and navigate to the next screen.
 *
 * @param navController The NavController used for navigation between screens.
 * @param viewModel The UserViewModel used to manage user-related data.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(navController: NavController, viewModel: UserViewModel) {
    var name by remember { mutableStateOf("") }
    var sentence by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFB2EBF2), Color(0xFF0288D1))
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // User Icon
            Image(
                painter = painterResource(id = R.drawable.ic_personadd),
                contentDescription = "User Icon",
                modifier = Modifier
                    .size(160.dp)
                    .background(Color.Transparent, CircleShape)
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))

            // Input Name
            TextField(
                value = name,
                onValueChange = {
                    name = it
                    viewModel.updateName(it) // Update the name in the ViewModel
                },
                placeholder = {
                    Text("Name", style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.Gray.copy(alpha = 0.5f),
                        fontWeight = FontWeight.Medium
                    )) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(14.dp))
                    .padding(horizontal = 8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = MaterialTheme.typography.bodyLarge,
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Input Palindrome
            TextField(
                value = sentence,
                onValueChange = { sentence = it },
                placeholder = { Text("Palindrome", style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.Gray.copy(alpha = 0.5f),
                    fontWeight = FontWeight.Medium
                )) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(14.dp))
                    .padding(horizontal = 8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Check Button
            Button(
                onClick = {
                    result = if (sentence.replace(" ", "").equals(
                            sentence.replace(" ", "").reversed(), true
                        )
                    ) {
                        "isPalindrome"
                    } else {
                        "not palindrome"
                    }
                    Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2B637B),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("CHECK", style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Medium,
                ))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Next Button
            Button(
                onClick = { navController.navigate("second_screen") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2B637B),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("NEXT", style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Medium,
                ))
            }
        }
    }
}