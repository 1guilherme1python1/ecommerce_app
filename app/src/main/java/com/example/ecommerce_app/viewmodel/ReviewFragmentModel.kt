package com.example.ecommerce_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce_app.model.Review
import com.example.ecommerce_app.model.ReviewFirebase
import kotlinx.coroutines.launch

class ReviewFragmentModel: ViewModel() {

    val listaReview = MutableLiveData<List<Review>>()

    fun recuperarReviews(){
        val reviewFirebase = ReviewFirebase()

        viewModelScope.launch {
            val reviews = reviewFirebase.recuperarItems()

            listaReview.postValue(reviews)
        }
    }
}