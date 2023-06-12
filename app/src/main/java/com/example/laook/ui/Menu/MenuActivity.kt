package com.example.laook.ui.Menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laook.ui.detail.DetailActivity
import com.example.laook.R
import com.example.laook.databinding.ActivityMenuBinding
import com.example.laook.response.Menu

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

        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_recommendation)
        adapter = MenuAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        completeIngredients = intent.getStringArrayListExtra(EXTRA_INGREDIENTS) ?: emptyList()

        displayMenusByIngredients()

        val btnBack: ImageView = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            onBackPressed() // Perform the back button action (go back)
        }


    }

    private fun displayMenusByIngredients() {
        showLoading(true)

        viewModel.getMenusByIngredients(completeIngredients).observe(this, { menus ->
            val filteredMenus = menus.filter { menu ->
                completeIngredients.all { ingredient ->
                    menu.ingredients.contains(ingredient)
                }
            }

            adapter.setMenus(filteredMenus)

            if (filteredMenus.isEmpty()) {
                binding.tvNoData.visibility = View.VISIBLE
            } else {
                binding.tvNoData.visibility = View.GONE
            }

            adapter.setOnClickListener { menu ->
                startDetailActivity(menu)
            }

            showLoading(false)

        })
    }


    private fun startDetailActivity(menu: Menu) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MENU, menu)
        startActivity(intent)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


}