package com.example.ecommerce_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce_app.R
import com.example.ecommerce_app.databinding.ActivityCartBinding
import com.example.ecommerce_app.services.SharedPreferencesService
import com.example.ecommerce_app.views.adapter.CartAdapter

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private val binding by lazy {
        ActivityCartBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {

        val listItemsCard = SharedPreferencesService.getProductsToCart(applicationContext);

        recyclerView = findViewById(R.id.rvCardView)
        recyclerView.adapter = listItemsCard?.let { CartAdapter(it) }
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
            )

        binding.btnBackCart.setOnClickListener {
            finish()
        }
    }


}