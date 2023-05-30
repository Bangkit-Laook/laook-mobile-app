package com.example.laook.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.laook.R
import com.example.laook.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        homeViewModel.imageList.add(SlideModel("https://cdn1-production-images-kly.akamaized.net/j4KFsV4Pl08lGnhCED4Gm0LR3kE=/0x148:1920x1230/1200x675/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/3093328/original/069244600_1585909700-fried-2509089_1920.jpg"))
        homeViewModel.imageList.add(SlideModel("https://cdn1-production-images-kly.akamaized.net/EjwV7j3Y4JrlqUFuavke4NtRWtM=/1200x675/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/3108566/original/079979700_1587487794-Sajiku_1.jpg"))
        homeViewModel.imageList.add(SlideModel("https://assets.pikiran-rakyat.com/crop/0x0:1080x690/750x500/photo/2023/05/18/3411355689.jpg"))

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val sliderLayout = root.findViewById<ImageSlider>(R.id.sliderLayout)
        sliderLayout.setImageList(homeViewModel.imageList)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}