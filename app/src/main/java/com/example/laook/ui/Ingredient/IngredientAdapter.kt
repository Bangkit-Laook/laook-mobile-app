package com.example.laook


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IngredientAdapter : RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {
    private val ingredients: MutableList<String> = mutableListOf()
    private var clickListener: OnIngredientClickListener? = null

    inner class IngredientViewHolder(itemView: View, private val clickListener: OnIngredientClickListener?) :
        RecyclerView.ViewHolder(itemView) {
        private val ingredientTextView: TextView = itemView.findViewById(R.id.tvIngredientName)
        private val deleteButton: ImageView = itemView.findViewById(R.id.btnDeleteIngredient)

        init {
            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val ingredient = ingredients[position]
                    clickListener?.onIngredientClick(ingredient)
                }
            }
        }

        fun bind(ingredient: String) {
            ingredientTextView.text = ingredient
        }
    }

    fun setOnIngredientClickListener(listener: OnIngredientClickListener) {
        clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ingredient, parent, false)
        return IngredientViewHolder(itemView, clickListener)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredient = ingredients[position]
        holder.bind(ingredient)
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    fun setIngredients(newIngredients: List<String>) {
        ingredients.clear()
        ingredients.addAll(newIngredients)
        notifyDataSetChanged()
    }

    interface OnIngredientClickListener {
        fun onIngredientClick(ingredient: String)
    }
}