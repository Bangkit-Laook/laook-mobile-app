package com.example.laook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIngredientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = IngredientAdapter(ingredientList)
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
        val ingredients = arrayOf("Ingredient 1", "Ingredient 2", "Ingredient 3")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select Ingredient")
        builder.setItems(ingredients) { dialog, which ->
            val selectedIngredient = ingredients[which]
            addIngredient(selectedIngredient)
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun addIngredient(ingredient: String) {
        ingredientList.add(ingredient)
        adapter.notifyDataSetChanged()
    }
}

