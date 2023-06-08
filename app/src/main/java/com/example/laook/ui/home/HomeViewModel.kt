package com.example.laook.ui.home


import androidx.lifecycle.ViewModel
import com.denzcoskun.imageslider.models.SlideModel

class HomeViewModel : ViewModel() {

    val imageList = mutableListOf<SlideModel>()

    init {
        // Tambahkan gambar default ke imageList jika diperlukan
        if (imageList.isEmpty()) {
            imageList.add(SlideModel("https://i.ibb.co/7GN6hSj/7967011.jpg"))
            imageList.add(SlideModel("https://i.ibb.co/B60qWRJ/5836803.jpg"))
            imageList.add(SlideModel("https://i.ibb.co/NmTRVSS/5807307.jpg"))
        }
    }
}