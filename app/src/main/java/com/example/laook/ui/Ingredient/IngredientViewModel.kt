package com.example.laook.ui.Ingredient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IngredientViewModel : ViewModel() {
    private val _ingredients = MutableLiveData<List<String>>()

    val ingredients: LiveData<List<String>>
        get() = _ingredients

    fun setScanIngredients(scanIngredient: List<String>) {
        _ingredients.value = scanIngredient
    }

    fun getAllIngredients(): List<String> {
        // All ingredient
        val allIngredients = listOf("chicken", "Egg", "meat","onion", "garlic", "ginger", "tomatoes", "Shrimp","curry powder","chili powder","Squid","paprika","potatoes","oil", "Tahu")

        return allIngredients
    }

    fun addIngredient(ingredient: String) {
        val currentIngredients = _ingredients.value?.toMutableList() ?: mutableListOf()
        currentIngredients.add(ingredient)
        _ingredients.value = currentIngredients
    }

    fun removeIngredient(ingredient: String) {
        val currentIngredients = _ingredients.value?.toMutableList() ?: mutableListOf()
        currentIngredients.remove(ingredient)
        _ingredients.value = currentIngredients
    }


}
