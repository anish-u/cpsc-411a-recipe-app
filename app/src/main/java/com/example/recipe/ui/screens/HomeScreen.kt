package com.example.recipe.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recipe.viewmodel.RecipeAppViewModel
import com.example.recipe.ui.theme.PrimaryTeal
import com.example.recipe.ui.theme.SecondaryPink
import com.example.recipe.ui.theme.SurfaceDark
import com.example.recipe.ui.theme.TextPrimary
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.OutlinedTextField
import com.example.recipe.ui.theme.CardColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: RecipeAppViewModel) {
    var query by remember { mutableStateOf("") }

    val filtered = viewModel.recipes.filter {
        it.name.contains(query, ignoreCase = true) ||
                it.cuisine.contains(query, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recipe App", fontWeight = FontWeight.Bold, color = TextPrimary) },
                actions = {
                    IconButton(onClick = { navController.navigate("favorites") }) {
                        Icon(Icons.Default.Favorite, contentDescription = "Favorites", tint = PrimaryTeal)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SurfaceDark
                )
            )
        },
        containerColor = SurfaceDark
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                label = { Text("Search dishes or cuisines") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                shape = RoundedCornerShape(12.dp)
            )

            LazyColumn(
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(filtered) { recipe ->
                    RecipeCard(recipe.name, recipe.cuisine, recipe.imageRes) {
                        navController.navigate("details/${recipe.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun RecipeCard(name: String, cuisine: String, imageRes: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardColor),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(name, fontWeight = FontWeight.Bold, color = PrimaryTeal)
                Text("Cuisine: $cuisine", color = SecondaryPink)
            }
        }
    }
}
