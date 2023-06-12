package com.example.laook

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.laook.ui.Scan.ScanActivity
import com.example.laook.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_home)


        navView.setupWithNavController(navController)

        binding.scanMenu.setOnClickListener{
            startActivity(Intent(this, ScanActivity::class.java))
        }




        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

}
