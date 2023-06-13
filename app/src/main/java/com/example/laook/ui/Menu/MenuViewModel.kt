package com.example.laook.ui.Menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laook.response.Menu
import com.example.laook.response.SuggestMenusRequest
import com.example.laook.retrofit.ApiConfig
import com.example.laook.retrofit.ApiService
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {
    private val apiService: ApiService = ApiConfig.createApiService()
    private val menusLiveData = MutableLiveData<List<Menu>>()

    fun getMenusByIngredients(ingredients: List<String>): LiveData<List<Menu>> {
        viewModelScope.launch {
            try {
                val request = SuggestMenusRequest(ingredients)

                val response = apiService.suggestMenus(request)

                if (response.isSuccessful) {
                    val menuResponse = response.body()
                    menuResponse?.let {
                        menusLiveData.postValue(it.menus)
                    }
                } else {
                    // Handle error response
                }
            } catch (e: Exception) {
                // Handle exception
            }
        }
        return menusLiveData
    }
}