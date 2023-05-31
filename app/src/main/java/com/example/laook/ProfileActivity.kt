package com.example.laook

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var etEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()
        etEmail = findViewById(R.id.etEmail)



        val tvLogout: TextView = findViewById(R.id.tvLogout) // Ganti dengan id yang sesuai di XML
        tvLogout.setOnClickListener {
            auth.signOut()
            Intent(this@ProfileActivity, LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }

        val user = auth.currentUser

        if (user != null) {
            etEmail.setText(user.email)
        }

        //btn info


        // Find the back button ImageView in activity_profile.xml
        val btnBack: ImageView = findViewById(R.id.btnBack)

        // Set click listener for the back button
        btnBack.setOnClickListener {
            onBackPressed() // Perform the back button action (go back)
        }


    }




}