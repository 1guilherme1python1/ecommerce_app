package com.example.ecommerce_app.model

import android.util.Log
import com.example.ecommerce_app.services.FirebaseService
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class BannerFirebase {
    suspend fun recuperarBanners(): List<Banner> = suspendCoroutine { continuation ->
        val dataBase = FirebaseService.recuperaDataBaseFirebase()

        dataBase.child("Banner").get().addOnSuccessListener { bannerSnapshot ->
            val banners = mutableListOf<Banner>()
            for (banner in bannerSnapshot.children) {
                val bannerUrl = banner.child("url").getValue(String::class.java)
                bannerUrl?.let {
                    val bannerInstance = Banner(url = it)
                    banners.add(bannerInstance)
                }
            }
            continuation.resume(banners)
        }.addOnFailureListener { exception ->
            exception.printStackTrace()
            continuation.resume(emptyList())
        }
    }

}