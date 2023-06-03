package com.example.laook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laook.databinding.ActivityIngredientBinding
import com.example.laook.response.Menu
import com.example.laook.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IngredientActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIngredientBinding
    private lateinit var adapter: IngredientAdapter
    private var ingredientList: MutableList<String> = mutableListOf()
    private var completeIngredientList: MutableList<String> = mutableListOf()

    // Daftar ingredient yang tersedia
    private val availableIngredients = listOf(
        "Ingredient 1",
        "Ingredient 2",
        "Ingredient 3",
        "Ingredient 4",
        "apel",
        // Tambahkan ingredient lainnya sesuai kebutuhan
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIngredientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = IngredientAdapter(ingredientList, ::deleteIngredient)
        binding.rvIngredient.layoutManager = LinearLayoutManager(this)
        binding.rvIngredient.adapter = adapter

        binding.fabAddIngredient.setOnClickListener {
            showIngredientSelectionDialog()
        }

        val apiConfig = ApiConfig()

        fetchIngredientsForMenu("1", apiConfig)
    }

    private fun fetchIngredientsForMenu(menuId: String, apiConfig: ApiConfig) {
        apiConfig.getMenu(menuId, object : Callback<List<Menu>> {
            override fun onResponse(call: Call<List<Menu>>, response: Response<List<Menu>>) {
                if (response.isSuccessful) {
                    val menus = response.body()
                    if (menus != null && menus.isNotEmpty()) {
                        val ingredients = menus[0].ingredients
                        ingredientList.addAll(ingredients)
                        completeIngredientList.addAll(ingredients)
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(this@IngredientActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Menu>>, t: Throwable) {
                Toast.makeText(this@IngredientActivity, "Failed to fetch data: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showIngredientSelectionDialog() {
        val availableIngredientArray = availableIngredients.toTypedArray()

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select Ingredient")
        builder.setItems(availableIngredientArray) { dialog, which ->
            val selectedIngredient = availableIngredientArray[which]
            if (!ingredientList.contains(selectedIngredient)) {
                addIngredient(selectedIngredient)
            } else {
                Toast.makeText(this@IngredientActivity, "Ingredient already added", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun addIngredient(ingredient: String) {
        ingredientList.add(ingredient)
        completeIngredientList.add(ingredient)
        adapter.notifyDataSetChanged()
    }

    private fun deleteIngredient(ingredient: String) {
        ingredientList.remove(ingredient)
        completeIngredientList.remove(ingredient)
        adapter.notifyDataSetChanged()
    }
}