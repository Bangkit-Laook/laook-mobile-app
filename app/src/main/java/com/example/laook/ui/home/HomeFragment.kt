package com.example.laook.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel

import com.example.laook.Profile.ProfileActivity
import com.example.laook.R
import com.example.laook.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val tvFullname = binding.tvFullname

        if (currentUser != null) {
            tvFullname.text = currentUser.displayName
        }

        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        homeViewModel.imageList.add(SlideModel("https://i.ibb.co/7GN6hSj/7967011.jpg"))
        homeViewModel.imageList.add(SlideModel("https://i.ibb.co/B60qWRJ/5836803.jpg"))
        homeViewModel.imageList.add(SlideModel("https://i.ibb.co/NmTRVSS/5807307.jpg"))

        val sliderLayout = root.findViewById<ImageSlider>(R.id.sliderLayout)
        sliderLayout.setImageList(homeViewModel.imageList)

        binding.profile.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}