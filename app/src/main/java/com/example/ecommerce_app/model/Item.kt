package com.example.ecommerce_app.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val description: String = "",
    val oldPrice: Int = 0,
    val price: Int = 0,
    val rating: Double = 0.0,
    val review: Int = 0,
    val title: String = "",
    val picUrl: List<String> = emptyList()
) : Parcelable
