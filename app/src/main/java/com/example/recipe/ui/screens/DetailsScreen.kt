package com.example.recipe.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: RecipeAppViewModel,
    id: Int
) {
    val recipe = viewModel.getRecipeById(id) ?: return

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(recipe.name, fontWeight = FontWeight.Bold, color = TextPrimary) },
                navigationIcon = {
                    TextButton(onClick = { navController.popBackStack() }) {
                        Text("Back", color = PrimaryTeal)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SurfaceDark)
            )
        },
        containerColor = SurfaceDark
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(recipe.imageRes),
                contentDescription = recipe.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Cuisine: ${recipe.cuisine}",
                    style = MaterialTheme.typography.titleLarge,
                    color = SecondaryPink
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = recipe.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Ingredients",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    color = PrimaryTeal
                )
                Spacer(modifier = Modifier.height(8.dp))
                recipe.ingredients.forEach {
                    Text("• $it", style = MaterialTheme.typography.bodyLarge, color = TextPrimary)
                }

                Spacer(modifier = Modifier.height(24.dp))

                val isFav = recipe.isFavorite
                val favColor by animateColorAsState(
                    targetValue = if (isFav) Color.Red else Color.Gray,
                    animationSpec = spring()
                )

                Button(
                    onClick = { viewModel.toggleFavorite(recipe.id) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryTeal)
                ) {
                    Icon(
                        imageVector = if (isFav) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = favColor
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(if (isFav) "Remove from Favorites" else "Add to Favorites", color = SurfaceDark)
                }
            }
        }
    }
}
