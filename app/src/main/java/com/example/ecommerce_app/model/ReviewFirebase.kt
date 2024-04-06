package com.example.ecommerce_app.model

import android.util.Log
import com.example.ecommerce_app.services.FirebaseService
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ReviewFirebase {
    suspend fun recuperarItems(): List<Review> = suspendCoroutine{continuation ->
        val dataBase = FirebaseService.recuperaDataBaseFirebase()

        dataBase.child("Review").get().addOnSuccessListener { reviewSnapshot ->
            val reviews: MutableList<Review> = mutableListOf()

            for (review in reviewSnapshot.children){
                val reviewModel = review.getValue(Review::class.java)

                reviewModel?.let {
                    reviews.add(reviewModel)
                }

                Log.i("reviews", "$reviewModel")
            }

            continuation.resume(reviews)
        }.addOnFailureListener {exception ->
            exception.printStackTrace()
            continuation.resume(emptyList())
        }

    }
}