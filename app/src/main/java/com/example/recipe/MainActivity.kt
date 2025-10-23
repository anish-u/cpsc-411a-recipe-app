package com.example.recipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.recipe.navigation.RecipeAppNavGraph
import com.example.recipe.ui.theme.RecipeAppTheme
import com.example.recipe.viewmodel.RecipeAppViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeApp()
        }
    }
}

@Composable
fun RecipeApp() {
    RecipeAppTheme {
        val navController = rememberNavController()
        val viewModel: RecipeAppViewModel = viewModel()
        var selectedItem by remember { mutableStateOf("home") }

        Scaffold(
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = selectedItem == "home",
                        onClick = {
                            selectedItem = "home"
                            navController.navigate("home") {
                                popUpTo("home") { inclusive = false }
                            }
                        },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home") }
                    )
                    NavigationBarItem(
                        selected = selectedItem == "favorites",
                        onClick = {
                            selectedItem = "favorites"
                            navController.navigate("favorites") {
                                popUpTo("home")
                            }
                        },
                        icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
                        label = { Text("Favorites") }
                    )
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                RecipeAppNavGraph(navController = navController, viewModel = viewModel)
            }
        }
    }
}
