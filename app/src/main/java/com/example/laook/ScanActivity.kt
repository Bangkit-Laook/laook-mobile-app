package com.example.laook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.appbar.MaterialToolbar

class ScanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

//        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)

        // Find the back button ImageView
        val btnBack: ImageView = findViewById(R.id.btnBack)

        // Set click listener for the back button
        btnBack.setOnClickListener {
            onBackPressed() // Perform the back button action (go back)
        }
    }
}