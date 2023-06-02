package com.example.laook

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IngredientAdapter(private val ingredients: MutableList<String>) : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient = ingredients[position]
        holder.bind(ingredient)
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ingredientTextView: TextView = itemView.findViewById(R.id.ingredientTextView)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)

        init {
            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    removeIngredient(position)
                }
            }
        }

        fun bind(ingredient: String) {
            ingredientTextView.text = ingredient
        }
    }

    fun removeIngredient(position: Int) {
        ingredients.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addIngredient(ingredient: String) {
        ingredients.add(ingredient)
        notifyItemInserted(ingredients.size - 1)
    }

    fun showAddIngredientDialog(context: Context) {
        val dialog = AlertDialog.Builder(context)
        val editText = EditText(context)
        dialog.setTitle("Tambah Bahan")
            .setView(editText)
            .setPositiveButton("Tambah") { _, _ ->
                val ingredient = editText.text.toString()
                addIngredient(ingredient)
            }
            .setNegativeButton("Batal", null)
            .create()
            .show()
    }
}