package com.example.ecommerce_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecommerce_app.R
import com.example.ecommerce_app.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCartBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}