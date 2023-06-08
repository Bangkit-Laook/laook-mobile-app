package com.example.laook.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.laook.ui.Authentication.LoginActivity
import com.example.laook.R
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var etEmail: TextView
    private lateinit var tvFullname: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        auth = FirebaseAuth.getInstance()
        etEmail = view.findViewById(R.id.etEmail)
        tvFullname = view.findViewById(R.id.tvFullname)

        val tvLogout: TextView = view.findViewById(R.id.tvLogout)
        tvLogout.setOnClickListener {
            auth.signOut()
            Intent(requireContext(), LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }

        val user = auth.currentUser

        if (user != null) {
            etEmail.text = user.email
            tvFullname.text = user.displayName
        }

        return view
    }
}