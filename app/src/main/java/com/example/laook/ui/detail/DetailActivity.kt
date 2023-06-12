package com.example.laook.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.laook.R
import com.example.laook.databinding.ActivityDetailBinding
import com.example.laook.response.Menu

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    companion object {
        const val EXTRA_MENU = "extra_menu"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



//        val nameTextView = binding.tvDetailName
//        val nameTextView = binding.tvDetailName



        val menu: Menu? = intent.getParcelableExtra(EXTRA_MENU)
        val nameTextView = binding.tvDetailName
        val imageView = binding.ivDetailPhoto
        val ingredientsTextView = binding.tvDetailListIngredient
        val stepsTextView = binding.tvDetailListCooking


        if (menu != null) {


            nameTextView.text = menu.name

            Glide.with(this)
                .load(menu.image_url)
                .into(imageView)


            val ingredients = menu.ingredients.joinToString(", ")
            ingredientsTextView.text = ingredients


            val steps = menu.steps.joinToString("\n\n")
            stepsTextView.text = steps

            // Gunakan data menu untuk menampilkan detail menu
            // Contoh: nameTextView.text = menu.name
            //         descriptionTextView.text = menu.description
        }

        // Find the back button ImageView
        val btnBack: ImageView = findViewById(R.id.btnBack)

        // Set click listener for the back button
        btnBack.setOnClickListener {
            onBackPressed() // Perform the back button action (go back)
        }
    }

}