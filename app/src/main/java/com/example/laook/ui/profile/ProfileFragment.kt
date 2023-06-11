package com.example.laook.ui.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.laook.ui.Authentication.LoginActivity
import com.example.laook.R
import com.example.laook.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment(), EditProfileFragment.ProfileUpdateListener{

    private lateinit var auth: FirebaseAuth
    private lateinit var etEmail: TextView
    private lateinit var tvFullname: TextView
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        auth = FirebaseAuth.getInstance()

        etEmail = binding.tvEmail
        tvFullname = binding.tvFullname

        val tvLogout: ConstraintLayout = binding.logout
        tvLogout.setOnClickListener {
            auth.signOut()
            Intent(requireContext(), LoginActivity::class.java).also { intent ->
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        val btnEditProfile: ImageView = binding.editProfile
        btnEditProfile.setOnClickListener {
            val editProfileFragment = EditProfileFragment()
            editProfileFragment.profileUpdateListener = this
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(R.id.container, editProfileFragment, EditProfileFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

        val user = auth.currentUser
        if (user != null) {
            etEmail.text = user.email
            tvFullname.text = user.displayName
            Picasso.get().load(user.photoUrl).into(binding.photoProfile)
        }

        val tvLanguage: ConstraintLayout = binding.language
            tvLanguage.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))

        }




        return root
    }

    override fun onProfileUpdated(name: String?, photoUrl: Uri?) {
        tvFullname.text = name
        Picasso.get().load(photoUrl).into(binding.photoProfile)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
