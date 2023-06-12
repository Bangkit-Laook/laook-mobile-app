package com.example.laook.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.laook.MainActivity
import com.example.laook.R
import com.example.laook.databinding.ActivityDetailBinding
import com.example.laook.response.Menu
import com.example.laook.ui.Scan.ScanActivity
import com.example.laook.ui.home.HomeFragment

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_MENU = "extra_menu"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


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

            binding.btnBackHome.setOnClickListener{
                startActivity(Intent(this, MainActivity::class.java))
            }

        }

        val btnBack: ImageView = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

}