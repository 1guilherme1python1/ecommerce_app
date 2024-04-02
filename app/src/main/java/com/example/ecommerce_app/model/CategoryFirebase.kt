package com.example.ecommerce_app.model;

import android.util.Log
import com.example.ecommerce_app.services.FirebaseService
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CategoryFirebase {

    suspend fun recuperarCategories() : List<Category> = suspendCoroutine { continuation ->

        val dataBase = FirebaseService.recuperaDataBaseFirebase()

        dataBase.child("Category").get().addOnSuccessListener { categorySnapshot ->
            val categories = mutableListOf<Category>()

            for (category in categorySnapshot.children){
                val categoryModel = category.getValue(Category::class.java)

                categoryModel?.let {
                    categories.add(categoryModel)
                }
            }

            continuation.resume(categories)

        }.addOnFailureListener {exception ->
            exception.printStackTrace()
            continuation.resume(emptyList())
        }



//        dataBase.child("Banner").get().addOnSuccessListener { bannerSnapshot ->
//            val banners = mutableListOf<Banner>()
//            for (banner in bannerSnapshot.children) {
//                val bannerUrl = banner.child("url").getValue(String::class.java)
//                bannerUrl?.let {
//                    val bannerInstance = Banner(url = it)
//                    banners.add(bannerInstance)
//                }
//            }
//            continuation.resume(banners)
//        }.addOnFailureListener { exception ->
//            exception.printStackTrace()
//            continuation.resume(emptyList())
//        }

    }

}
