package com.example.laook.Ingredient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IngredientViewModel : ViewModel() {
    private val _ingredients = MutableLiveData<List<String>>()
    val ingredients: LiveData<List<String>>
        get() = _ingredients

    init {
        // Contoh daftar bahan yang diambil dari sumber data
        val listIngredients = mutableListOf("ing8_0")
        _ingredients.value = listIngredients
    }

    fun getAllIngredients(): List<String> {
        // Simulasikan pengambilan daftar semua bahan dari sumber data
        val allIngredients = listOf("ing0_1", "ing0_2", "ing0_3", "ing0_4","ing1_0","ing1_1","ing1_2","ing2_0","ing2_1","ing3_0")

        return allIngredients
    }

    fun addIngredient(ingredient: String) {
        val currentIngredients = _ingredients.value?.toMutableList() ?: mutableListOf()
        currentIngredients.add(ingredient)
        _ingredients.value = currentIngredients
    }


}
