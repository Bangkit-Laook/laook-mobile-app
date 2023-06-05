package com.example.laook.Menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.DocumentsContract.Root
import android.view.Menu
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laook.R
import com.example.laook.ResultActivity
import com.example.laook.databinding.ActivityMenuBinding
import com.example.laook.databinding.ActivityResultBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var viewModel: MenuViewModel
    private lateinit var adapter: MenuAdapter
    private lateinit var binding: ActivityMenuBinding
    private lateinit var completeIngredients: List<String>

    companion object {
        const val EXTRA_INGREDIENTS = "extra_ingredients"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi ViewModel
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)

        // Inisialisasi RecyclerView dan adapter
        val recyclerView = findViewById<RecyclerView>(R.id.rv_recommendation)
        adapter = MenuAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Ambil completeIngredients dari intent
        completeIngredients = intent.getStringArrayListExtra(EXTRA_INGREDIENTS) ?: emptyList()

        // Panggil metode untuk menampilkan menu berdasarkan bahan-bahan
        displayMenusByIngredients()


//        val completeIngredients = intent.getStringArrayListExtra(EXTRA_INGREDIENTS)
////        val result = completeIngredients
    }

    private fun displayMenusByIngredients() {
        // Daftar bahan-bahan yang ingin dicari
//        val ingredients = listOf("ing0_1") sudah benar

//        val ingredients = completeIngredients.toList()

        // Panggil metode di ViewModel untuk mendapatkan menu berdasarkan bahan-bahan
        viewModel.getMenusByIngredients(completeIngredients).observe(this, { menus ->
            // Filter menus based on ingredients
            val filteredMenus = menus.filter { menu ->
                completeIngredients.all { ingredient ->
                    menu.ingredients.contains(ingredient)
                }
            }

            // Update data pada adapter
            adapter.setMenus(filteredMenus)
        })
    }

}