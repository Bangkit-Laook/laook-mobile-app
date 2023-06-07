package com.example.laook.Ingredient

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
        // Simulasikan pengambilan daftar semua bahan dari sumber data
        val allIngredients = listOf("ing_1", "ing_2", "ing_3", "ing_4", "ing_5","ing_6","ing_7","ing_8","ing_9","ing_10","ing_11")

        return allIngredients
    }

    fun addIngredient(ingredient: String) {
        val currentIngredients = _ingredients.value?.toMutableList() ?: mutableListOf()
        currentIngredients.add(ingredient)
        _ingredients.value = currentIngredients
    }


}
