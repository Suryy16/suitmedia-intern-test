package com.example.suitmediaapp.ui.screens

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.suitmediaapp.R
import com.example.suitmediaapp.viewmodel.UserViewModel
import kotlinx.coroutines.launch

/**
 * ThirdScreen is a composable function that displays the third screen of the application.
 * It includes a top app bar with a back button, a list of users, and handles user selection.
 * The screen supports pull-to-refresh and infinite scrolling to load more users.
 *
 * @param navController The NavController used for navigation between screens.
 * @param viewModel The UserViewModel used to manage user-related data.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirdScreen(navController: NavController, viewModel: UserViewModel) {
    val users by viewModel.users.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // Fetch users when the screen is displayed
    LaunchedEffect(Unit) {
        viewModel.fetchUsers()
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.background(Color.White)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                TopAppBar(
                    title = {
                        Box(modifier = Modifier.fillMaxWidth().padding(end = 50.dp), contentAlignment = Alignment.Center) {
                            Text(
                                text = "Third Screen",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
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
        if (users.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No users available",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                )
            }
        } else {
            LazyColumn(
                state = listState,
                contentPadding = paddingValues,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 50.dp)
            ) {
                items(users) { user ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.selectUser(user)
                                navController.popBackStack()
                            }
                            .padding(vertical = 32.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // User Avatar
                        Image(
                            painter = rememberImagePainter(user.avatar),
                            contentDescription = null,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Gray, CircleShape)
                        )

                        Spacer(modifier = Modifier.width(32.dp))

                        // User Information
                        Column {
                            Text(
                                text = "${user.first_name} ${user.last_name}",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 24.sp,
                                    color = Color(0xFF04021D)
                                )
                            )
                            Text(
                                text = user.email,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = Color(0xFF686777),
                                    fontSize = 18.sp
                                )
                            )
                        }
                    }

                    // Divider between items
                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }

            // Pull to refresh and load more
            LaunchedEffect(listState) {
                snapshotFlow { listState.firstVisibleItemIndex }
                    .collect { index ->
                        if (index == 0 && !isRefreshing) {
                            viewModel.refreshUsers()
                        }
                    }
            }

            LaunchedEffect(listState) {
                snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
                    .collect { index ->
                        if (index == users.size - 1 && !isRefreshing) {
                            viewModel.loadNextPage()
                        }
                    }
            }
        }
    }
}