package com.example.recipe.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.recipe.R
import com.example.recipe.data.Recipe

class RecipeAppViewModel : ViewModel() {

    private val _recipes = mutableStateListOf(
        Recipe(
            1, "Spaghetti Carbonara", "Italian",
            "A creamy pasta dish made with eggs, cheese, pancetta, and pepper.",
            R.drawable.spaghetti,
            listOf("Spaghetti", "Eggs", "Parmesan Cheese", "Pancetta", "Black Pepper")
        ),
        Recipe(
            id = 2,
            name = "Chicken Tikka Masala",
            cuisine = "Indian",
            description = "Tender chicken pieces marinated in yogurt and spices, cooked in a creamy tomato sauce.",
            imageRes = R.drawable.chicken_tikka_masala,
            ingredients = listOf("Chicken", "Yogurt", "Tomatoes", "Cream", "Spices"),
            isFavorite = false
        ),
        Recipe(
            id = 3,
            name = "Sushi Roll",
            cuisine = "Japanese",
            description = "Classic sushi rolls with rice, seaweed, and fresh fish or vegetables.",
            imageRes = R.drawable.sushi_roll,
            ingredients = listOf("Sushi Rice", "Nori", "Salmon", "Avocado", "Soy Sauce"),
            isFavorite = true
        ),
        Recipe(
            id = 4,
            name = "Tacos al Pastor",
            cuisine = "Mexican",
            description = "Corn tortillas filled with marinated pork, pineapple, and cilantro.",
            imageRes = R.drawable.taco,
            ingredients = listOf("Pork", "Pineapple", "Cilantro", "Onion", "Tortillas"),
            isFavorite = false
        )
    )
    val recipes: List<Recipe> get() = _recipes

    fun getRecipeById(id: Int): Recipe? =
        _recipes.find { it.id == id }

    fun toggleFavorite(id: Int) {
        val index = _recipes.indexOfFirst { it.id == id }
        if (index != -1) {
            val current = _recipes[index]
            _recipes[index] = current.copy(isFavorite = !current.isFavorite)
        }
    }

    fun getFavorites(): List<Recipe> = _recipes.filter { it.isFavorite }
}
