package com.example.laook.ui.Ingredient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laook.IngredientAdapter
import com.example.laook.R
import com.example.laook.ui.Menu.MenuActivity
import com.example.laook.databinding.ActivityIngredientBinding


class IngredientActivity : AppCompatActivity() {
    private lateinit var viewModel: IngredientViewModel
    private lateinit var adapter: IngredientAdapter
    private lateinit var binding: ActivityIngredientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngredientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi ViewModel
        viewModel = ViewModelProvider(this).get(IngredientViewModel::class.java)

        // Inisialisasi RecyclerView dan adapter
        binding.rvIngredient.layoutManager = LinearLayoutManager(this)
        adapter = IngredientAdapter()
        binding.rvIngredient.adapter = adapter

        // Panggil metode untuk menampilkan daftar bahan yang telah dipilih sebelumnya
        displaySelectedIngredients()

        adapter.setOnIngredientClickListener(object : IngredientAdapter.OnIngredientClickListener {
            override fun onIngredientClick(ingredient: String) {
                viewModel.removeIngredient(ingredient)
            }
        })

        // Tambahkan button untuk menambahkan bahan
        binding.fabAddIngredient.setOnClickListener {
            showAddIngredientDialog()
        }

        binding.btnConfirm.setOnClickListener {
           navigateToResultActivity()
        }

        val scanIngredients = intent.getStringArrayListExtra("ingredients")
        val scanIngredientsList = scanIngredients?.toList()


        if (scanIngredientsList != null) {
            viewModel.setScanIngredients(scanIngredientsList)
        }


        val btnBack: ImageView = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            onBackPressed()
        }




    }




    private fun displaySelectedIngredients() {
        viewModel.ingredients.observe(this, { ingredients ->
            adapter.setIngredients(ingredients)
            if(ingredients.isEmpty()) {
                binding.tvNoData.visibility = View.VISIBLE
            } else {
                binding.tvNoData.visibility = View.GONE
            }
        })
    }

    private fun showAddIngredientDialog() {
        val allIngredients = viewModel.getAllIngredients()

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add Ingredient")
        builder.setItems(allIngredients.toTypedArray()) { dialog, index ->
            val selectedIngredient = allIngredients[index]

            if (viewModel.ingredients.value?.contains(selectedIngredient) == true) {
                Toast.makeText(this, "Ingredient already added", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addIngredient(selectedIngredient)
            }

            dialog.dismiss()
        }
        builder.create().show()
    }


    private fun navigateToResultActivity() {
        showLoading(true)
        val intent = Intent(this, MenuActivity::class.java)
        intent.putStringArrayListExtra(MenuActivity.EXTRA_INGREDIENTS, ArrayList(viewModel.ingredients.value))
        startActivity(intent)

        showLoading(false)
    }



    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}
