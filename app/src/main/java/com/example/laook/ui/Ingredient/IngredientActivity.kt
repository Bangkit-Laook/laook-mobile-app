package com.example.laook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laook.ui.Ingredient.IngredientViewModel
import com.example.laook.ui.Menu.MenuActivity
import com.example.laook.databinding.ActivityIngredientBinding
import com.example.laook.databinding.ItemIngredientBinding


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


    }

    private fun displaySelectedIngredients() {
        viewModel.ingredients.observe(this, { ingredients ->
            adapter.setIngredients(ingredients)
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
        val intent = Intent(this, MenuActivity::class.java)
        intent.putStringArrayListExtra(MenuActivity.EXTRA_INGREDIENTS, ArrayList(viewModel.ingredients.value))
        startActivity(intent)
    }
}
