package com.example.recipe.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipe.ui.screens.*
import com.example.recipe.viewmodel.RecipeAppViewModel

@Composable
fun RecipeAppNavGraph(navController: NavHostController, viewModel: RecipeAppViewModel) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController, viewModel)
        }
        composable("details/{id}") { backStack ->
            val id = backStack.arguments?.getString("id")?.toIntOrNull() ?: 0
            DetailsScreen(navController, viewModel, id)
        }
        composable("favorites") {
            FavoritesScreen(navController, viewModel)
        }
    }
}
