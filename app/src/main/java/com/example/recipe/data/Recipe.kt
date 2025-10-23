package com.example.recipe.data

import androidx.annotation.DrawableRes

data class Recipe(
    val id: Int,
    val name: String,
    val cuisine: String,
    val description: String,
    @DrawableRes val imageRes: Int,
    val ingredients: List<String>,
    val isFavorite: Boolean = false
)
