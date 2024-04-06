package com.example.ecommerce_app.model

data class Review(
    val ItemId: Int = 0,
    val Description: String = "",
    val Name: String = "",
    val PicUrl: String = "",
    val rating: Double = 0.0
)
