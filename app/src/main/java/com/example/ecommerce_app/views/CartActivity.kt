package com.example.ecommerce_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce_app.R
import com.example.ecommerce_app.databinding.ActivityCartBinding
import com.example.ecommerce_app.model.Item
import com.example.ecommerce_app.services.SharedPreferencesService
import com.example.ecommerce_app.views.adapter.CartAdapter

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var listaCart: List<Item>? = null

    private val binding by lazy {
        ActivityCartBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {

        listaCart  = SharedPreferencesService.getProductsToCart(applicationContext);

        if(!listaCart.isNullOrEmpty()){
            recyclerView = findViewById(R.id.rvCardView)
            recyclerView.adapter = listaCart?.let { CartAdapter(it) }
            recyclerView.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
            initFootCart()
        } else {
                binding.textEmptyCart.visibility = TextView.VISIBLE
        }



        binding.btnBackCart.setOnClickListener {
            finish()
        }
    }

    private fun initFootCart() {
        if(!listaCart.isNullOrEmpty()){
            binding.textSubTotalCart.text = String.format("R$ %.2f", subTotalCart().toFloat())
            binding.textDeliveryCart.text = "R$ 2,00"
            binding.textTaxaCart.text = "R$ 3,00"
            binding.textTotalCart.text = String.format("R$ %.2f", subTotalCart().toFloat() + 2 + 3)
        }
    }

    fun subTotalCart(): Int{
        var quantidade = 0

        listaCart!!.forEach { item ->
            quantidade += item.price
        }

        return quantidade
    }

}