package com.example.laook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class nerimaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nerima)

        // Mengambil data yang dikirimkan dari ScanActivity
        val ingredients = intent.getStringExtra("ingredients")

        // Gunakan data yang telah diterima sesuai kebutuhan Anda
        if (ingredients != null) {
            // Lakukan operasi atau tampilan yang sesuai dengan data yang diterima
            // Contoh: Tampilkan data pada TextView
            val textView = findViewById<TextView>(R.id.textView)
            textView.text = ingredients
        }
    }
}