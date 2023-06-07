package com.example.laook.Menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.laook.R
import com.example.laook.response.Menu

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    private val menus = mutableListOf<Menu>()


    private var listener: ((Menu) -> Unit)? = null

    fun setOnClickListener(listener: (Menu) -> Unit) {
        this.listener = listener
    }

    fun setMenus(newMenus: List<Menu>) {
        menus.clear()
        menus.addAll(newMenus)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_recommendation, parent, false)
        return MenuViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = menus[position]
        holder.bind(menu)

        // Panggil listener saat item menu diklik
        holder.itemView.setOnClickListener {
            listener?.invoke(menu)
        }
    }

    override fun getItemCount(): Int {
        return menus.size
    }

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tv_item_name)
//        private val nameTextViewDetail: TextView = itemView.findViewById(R.id.tv_detail_name)
//        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.iv_item_photo)


        fun bind(menu: Menu) {
            nameTextView.text = menu.name
//            nameTextViewDetail.text = menu.name
//            descriptionTextView.text = menu.description

            // Load image using a library like Picasso or Glide
            Glide.with(itemView.context)
                .load(menu.image_url)
                .into(imageView)


        }
    }
}