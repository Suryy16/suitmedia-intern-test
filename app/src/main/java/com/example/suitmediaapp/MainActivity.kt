package com.example.suitmediaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.suitmediaapp.ui.screens.FirstScreen
import com.example.suitmediaapp.ui.screens.SecondScreen
import com.example.suitmediaapp.ui.screens.ThirdScreen
import com.example.suitmediaapp.ui.theme.SuitmediaAppTheme
import com.example.suitmediaapp.viewmodel.UserViewModel

/**
 * MainActivity is the main entry point of the application.
 * It sets the content view to the SuitmediaAppTheme and displays the MainActivityScreen composable.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuitmediaAppTheme {
                MainActivityScreen()
            }
        }
    }
}

/**
 * MainActivityScreen is a composable function that sets up the navigation for the application.
 * It uses a NavHost to manage navigation between different screens.
 *
 * @param viewModel The UserViewModel instance used to manage user-related data.
 */
@Composable
fun MainActivityScreen(viewModel: UserViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "first_screen") {
        composable("first_screen") { FirstScreen(navController, viewModel) }
        composable("second_screen") { SecondScreen(navController, viewModel) }
        composable("third_screen") { ThirdScreen(navController, viewModel) }
    }
}