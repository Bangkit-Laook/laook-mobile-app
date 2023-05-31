package com.example.laook

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.appbar.MaterialToolbar

class ScanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        // Find the back button ImageView
        val btnBack: ImageView = findViewById(R.id.btnBack)

        // Set click listener for the back button
        btnBack.setOnClickListener {
            onBackPressed() // Perform the back button action (go back)
        }

        val btnInfo: ImageView = findViewById(R.id.btnInfo)

        btnInfo.setOnClickListener {
            showInfoDialog()
        }
    }

    private fun showInfoDialog() {
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_info, null)
        builder.setView(dialogView)

        val dialog = builder.create()
        dialog.setCancelable(true)

        val titleTextView = dialogView.findViewById<TextView>(R.id.tvTitleInfo)
        val understandButton = dialogView.findViewById<Button>(R.id.btnUnderstand)

        titleTextView.text = "Cara foto bahan masakan"

        understandButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}