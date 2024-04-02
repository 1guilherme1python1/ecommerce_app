package com.example.ecommerce_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce_app.model.Banner
import com.example.ecommerce_app.model.BannerFirebase
import com.example.ecommerce_app.model.Category
import com.example.ecommerce_app.model.CategoryFirebase
import com.example.ecommerce_app.model.Item
import com.example.ecommerce_app.model.ItemFirebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val listaBanner = MutableLiveData<List<Banner>>()
    val listaCategories = MutableLiveData<List<Category>>()
    val listaItems = MutableLiveData<List<Item>>()

    fun recuperarBanners(){
        val bannerFirebase = BannerFirebase()

        viewModelScope.launch {

            val banners = bannerFirebase.recuperarBanners()

            listaBanner.postValue(banners)
        }
    }

    fun recuperarCategories(){
        val categoriesFirebase = CategoryFirebase()

        viewModelScope.launch {
            val categories = categoriesFirebase.recuperarCategories()

            listaCategories.postValue(categories)
        }
    }

    fun recuperarItems(){
        val itemsFirebase = ItemFirebase()

        viewModelScope.launch {
            val items = itemsFirebase.recuperarItems()

            listaItems.postValue(items)
        }
    }
}